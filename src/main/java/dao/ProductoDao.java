package dao;

import model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static conexion.Conexion.getConnection;

public class ProductoDao {

    public static List<Producto> listarPollos() {
        List<Producto> list = new ArrayList<Producto>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT idProducto, nombre, descripcion, foto, precio FROM producto WHERE estado = 1 AND idCategoria = ?");
            ps.setInt(1, 1);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto prod = new Producto();
                prod.setIdProducto(rs.getInt("idProducto"));
                prod.setNombre(rs.getString("nombre"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setFoto(rs.getString("foto"));
                prod.setPrecio(rs.getDouble("precio"));
                list.add(prod);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static List<Producto> listarSopas() {
        List<Producto> list = new ArrayList<Producto>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT idProducto, nombre, descripcion, foto, precio FROM producto WHERE estado = 1 AND idCategoria = ?");
            ps.setInt(1, 2);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto prod = new Producto();
                prod.setIdProducto(rs.getInt("idProducto"));
                prod.setNombre(rs.getString("nombre"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setFoto(rs.getString("foto"));
                prod.setPrecio(rs.getDouble("precio"));
                list.add(prod);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Producto listarId(int id) {
        Producto p = null;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT idProducto, idCategoria, nombre, descripcion, foto, precio, stock FROM producto WHERE idProducto = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p = new Producto();
                p.setIdProducto(rs.getInt("idProducto"));
                p.setIdCategoria(rs.getInt("idCategoria"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setFoto(rs.getString("foto"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
            }
        } catch (Exception e) {

        }
        return p;
    }

    public static int registrarProducto(Producto prod) {
        int estado = 0;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO producto(idCategoria, nombre, descripcion, foto, precio, stock, estado) VALUES (?, ?, ?,?,?,?,?) ");
            ps.setInt(1, prod.getIdCategoria());
            ps.setString(2, prod.getNombre());
            ps.setString(3, prod.getDescripcion());
            ps.setString(4, prod.getFoto());
            ps.setDouble(5, prod.getPrecio());
            ps.setInt(6, prod.getStock());
            ps.setInt(7, prod.getEstado());

            estado = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return estado;
    }

    public static List<Producto> listarProductos() {
        List<Producto> listaProductos = new ArrayList<Producto>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT prod.idProducto, cat.nombre as nombreCategoria, prod.nombre, prod.descripcion, prod.foto, prod.precio, prod.stock, prod.estado FROM producto prod INNER JOIN categoria cat ON prod.idCategoria = cat.idCategoria WHERE prod.estado = 1 ORDER BY idProducto ASC");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto prod = new Producto();
                prod.setIdProducto(rs.getInt("idProducto"));
                prod.setNombreCategoria(rs.getString("nombreCategoria"));
                prod.setNombre(rs.getString("nombre"));
                prod.setDescripcion(rs.getString("descripcion"));
                prod.setFoto(rs.getString("foto"));
                prod.setPrecio(rs.getDouble("precio"));
                prod.setStock(rs.getInt("stock"));
                prod.setEstado(rs.getInt("estado"));
                listaProductos.add(prod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaProductos;
    }

}