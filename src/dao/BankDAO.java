package dao;

import models.banks.Bank;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankDAO implements IBankDAO{

    // Instance variable for the objetc BankDAO

    private static BankDAO instance;

    public BankDAO() {}

    // Apply Singleton Pattern
    public static BankDAO getInstance() {
        if (instance == null) {
            instance = new BankDAO();
        }
        return instance;
    }
    public void addBankEntity(Bank bank) {
        try {
            String query = "INSERT INTO banks(entity_name, country) VALUES(?, ?, ?)";
            PreparedStatement prStmt = DataConnection.getConnection().prepareStatement(query);
            DataConnection.getConnection().setAutoCommit(false);

            prStmt.setString(2, bank.getEntityName());
            prStmt.setString(3, bank.getCountry());

            prStmt.executeUpdate(query);

            prStmt.close();

        } catch (SQLException ex) {
            try {
                DataConnection.getConnection().rollback();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(ex.getMessage());
        } finally {
            try {
                DataConnection.getConnection().close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void deleteBankEntity(Bank bank) {
        try {
            String query = "DELETE FROM banks WHERE entity_code = ?";
            PreparedStatement prStmt = DataConnection.getConnection().prepareStatement(query);
            DataConnection.getConnection().setAutoCommit(false);

            prStmt.setLong(1, bank.getEntityCode());

            prStmt.executeUpdate(query);

            prStmt.close();

        } catch (SQLException ex) {
            try {
                DataConnection.getConnection().rollback();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(ex.getMessage());
        } finally {
            try {
                DataConnection.getConnection().close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void updateBankEntity(Bank bank){
        try {
            String query = "UPDATE banks SET entity_name ?, country = ? WHERE entity_code = ?";
            PreparedStatement prStmt = DataConnection.getConnection().prepareStatement(query);
            DataConnection.getConnection().setAutoCommit(false);

            prStmt.setString(1, bank.getEntityName());
            prStmt.setString(2, bank.getCountry());

            prStmt.executeUpdate(query);

            prStmt.close();

        } catch (SQLException ex) {
            try {
                DataConnection.getConnection().rollback();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(ex.getMessage());
        } finally {
            try {
                DataConnection.getConnection().close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Bank findBankByEntityCode(Long entityCode){
        Bank bank;
        try {
            String query = "SELECT * FROM banks WHERE entity_code = ?";
            PreparedStatement prStmt = DataConnection.getConnection().prepareStatement(query);
            DataConnection.getConnection().setAutoCommit(false);

            prStmt.setLong(1, entityCode);
            ResultSet rs = prStmt.executeQuery(query);

            if (rs.next()){
                bank = new Bank();
                bank.setEntityCode(rs.getLong(1));
                bank.setEntityName(rs.getString(2));
                bank.setEntityName(rs.getString(2));
                bank.setCountry(rs.getString(3));
                return bank;
            }
            prStmt.close();
            rs.close();

        } catch (SQLException ex) {
            try {
                DataConnection.getConnection().rollback();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(ex.getMessage());
        } finally {
            try {
                DataConnection.getConnection().close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}