package com.unicesumar.model.repositories;

import com.unicesumar.model.entities.Product;
import com.unicesumar.model.entities.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SaleRepository implements EntityRepository<Sale> {
    private final Connection connection;

    public SaleRepository(Connection connection){this.connection = connection;}
    @Override
    public void save(Sale entity) {
        String query = "INSERT INTO sales VALUES (?, ?, ?, ?, ?)";
        String querySaleProducts = "INSERT INTO sale_products VALUES (?, ?)";
        try {
            try (PreparedStatement saleStmt = connection.prepareStatement(query)) {
                saleStmt.setString(1, entity.getUuid().toString());
                saleStmt.setString(2, entity.getUser().getUuid().toString());
                saleStmt.setString(3, entity.getFormaPagamento().name());
                saleStmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                saleStmt.setDouble(5, entity.getValorTotal());
                saleStmt.executeUpdate();
            }
            try (PreparedStatement saleProductsStmt = connection.prepareStatement(querySaleProducts)) {
                for (Product product : entity.getProducts()) {
                    saleProductsStmt.setString(1, entity.getUuid().toString());
                    saleProductsStmt.setString(2, product.getUuid().toString());
                    saleProductsStmt.addBatch();
                }
                saleProductsStmt.executeBatch();
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Sale> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<Sale> findAll() {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }
}
