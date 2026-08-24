package com.sistema.facturacion

import android.content.Intent
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sistema.facturacion.database.ClienteEntity
import com.sistema.facturacion.database.FacturaEntity
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var editRnc: EditText
    lateinit var editNombreCliente: EditText
    lateinit var editProductoId: EditText
    lateinit var editCantidad: EditText
    lateinit var btnProcesar: Button
    lateinit var btnVerProductos: Button
    lateinit var btnVerFacturas: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editRnc = findViewById(R.id.editRnc)
        editNombreCliente = findViewById(R.id.editNombreCliente)
        editProductoId = findViewById(R.id.editProductoId)
        editCantidad = findViewById(R.id.editCantidad)
        btnProcesar = findViewById(R.id.btnProcesar)
        btnVerProductos = findViewById(R.id.btnVerProductos)
        btnVerFacturas = findViewById(R.id.btnVerFacturas)

        insertarProductoDemo()

        btnProcesar.setOnClickListener {
            procesarFactura()
        }

        btnVerProductos.setOnClickListener {
            startActivity(Intent(this, ProductosActivity::class.java))
        }

        btnVerFacturas.setOnClickListener {
            startActivity(Intent(this, FacturasActivity::class.java))
        }
    }

    fun procesarFactura() {
        val rnc = editRnc.text.toString()
        val nombreCliente = editNombreCliente.text.toString()
        val prodIdStr = editProductoId.text.toString()
        val cantStr = editCantidad.text.toString()

        if (rnc.isEmpty() || nombreCliente.isEmpty() || prodIdStr.isEmpty() || cantStr.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val prodId = prodIdStr.toInt()
        val cantidad = cantStr.toInt()

        Thread {
            val dao = FacturacionApp.database.facturacionDao()
            val producto = dao.getProductoById(prodId)

            if (producto == null) {
                runOnUiThread { Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show() }
                return@Thread
            }

            if (producto.stock < cantidad) {
                runOnUiThread { Toast.makeText(this, "No hay suficiente stock", Toast.LENGTH_SHORT).show() }
                return@Thread
            }

            val cliente = ClienteEntity(rnc, nombreCliente, "Dirección genérica")
            dao.insertCliente(cliente)

            val total = producto.precio * cantidad
            producto.stock = producto.stock - cantidad
            dao.updateProducto(producto)

            val fecha = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date())
            val factura = FacturaEntity(
                clienteRnc = rnc,
                productoId = prodId,
                cantidad = cantidad,
                total = total,
                fecha = fecha
            )
            dao.insertFactura(factura)

            // generar recibo en pdf
            generarReciboPdf(nombreCliente, producto.nombre, cantidad, total, fecha)

            runOnUiThread {
                Toast.makeText(this, "Factura creada. Total: $" + total, Toast.LENGTH_LONG).show()
                limpiarCampos()
            }
        }.start()
    }

    // funcion sencilla para crear el recibo en pdf
    fun generarReciboPdf(cliente: String, producto: String, cantidad: Int, total: Double, fecha: String) {
        val documento = PdfDocument()
        val pagina = documento.startPage(PdfDocument.PageInfo.Builder(300, 400, 1).create())
        val canvas = pagina.canvas
        val paint = Paint()
        paint.textSize = 14f

        var y = 30
        canvas.drawText("Recibo de Factura", 20f, y.toFloat(), paint)
        y += 30
        canvas.drawText("Cliente: $cliente", 20f, y.toFloat(), paint)
        y += 20
        canvas.drawText("Producto: $producto", 20f, y.toFloat(), paint)
        y += 20
        canvas.drawText("Cantidad: $cantidad", 20f, y.toFloat(), paint)
        y += 20
        canvas.drawText("Total: $$total", 20f, y.toFloat(), paint)
        y += 20
        canvas.drawText("Fecha: $fecha", 20f, y.toFloat(), paint)

        documento.finishPage(pagina)

        val carpeta = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val archivo = File(carpeta, "recibo_" + System.currentTimeMillis() + ".pdf")
        val salida = FileOutputStream(archivo)
        documento.writeTo(salida)
        documento.close()
        salida.close()
    }

    fun insertarProductoDemo() {
        Thread {
            val dao = FacturacionApp.database.facturacionDao()
            if (dao.getProductoById(1) == null) {
                dao.insertProducto(
                    com.sistema.facturacion.database.ProductoEntity(
                        idProducto = 1,
                        nombre = "Laptop",
                        precio = 500.0,
                        stock = 10
                    )
                )
            }
        }.start()
    }

    fun limpiarCampos() {
        editRnc.text.clear()
        editNombreCliente.text.clear()
        editProductoId.text.clear()
        editCantidad.text.clear()
    }
}
