package com.visa.prj.dao;

import com.visa.prj.entity.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbcImpl implements ProductDao{
    @Override
    public void addProduct(Product product) throws DaoException {
        Connection con = null;
        try {
            String SQL = "INSERT INTO products (id, name, price, quantity) VALUES(0, ?, ?, ?)";
            con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            ps.executeUpdate();
        } catch (SQLException ex) {
            if(ex.getErrorCode() ==1062) {
                throw new DaoException("Product with given id: " + product.getId() + " already exists!!", ex);
            } else if(ex.getErrorCode() == 1054) {
                throw new DaoException("Unable to add Product!!", ex);
            }

        } finally {
            DBUtil.closeConnection(con);
        }
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        Connection con = null;
        String SQL = "SELECT id, name, price, quantity FROM products";
        try {
            con = DBUtil.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                products.add(Product.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .price(rs.getDouble("price"))
                        .quantity(rs.getInt("quantity"))
                        .build());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBUtil.closeConnection(con);
        }
        return products;
    }
}
