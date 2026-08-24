package com.sistema.facturacion.database

import androidx.room.*

@Dao
interface FacturacionDao {

    @Query("SELECT * FROM producto_entity WHERE idProducto = :id")
    fun getProductoById(id: Int): ProductoEntity?

    @Query("SELECT * FROM producto_entity")
    fun getAllProductos(): List<ProductoEntity>

    @Insert
    fun insertProducto(producto: ProductoEntity): Long

    @Query("SELECT * FROM cliente_entity WHERE rncCedula = :rnc")
    fun getClienteByRnc(rnc: String): ClienteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCliente(cliente: ClienteEntity)

    @Insert
    fun insertFactura(factura: FacturaEntity): Long

    @Query("SELECT * FROM factura_entity ORDER BY idFactura DESC")
    fun getAllFacturas(): List<FacturaEntity>

    @Update
    fun updateProducto(producto: ProductoEntity)
}
