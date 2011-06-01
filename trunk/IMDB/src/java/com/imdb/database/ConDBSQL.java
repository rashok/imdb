/**
 * @(#)ConDBSQL.java      <version>  13/04/2011
 *
 * Copyright (c) 2011 ByteCode Team, Inc.
 * Sorocaba, São Paulo, Brazil.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ByteCode Team, Inc.
 */
package com.imdb.database;

import java.sql.*;

public class ConDBSQL {

    public static void main(String args[]) {

        // Criando as variáveis de conexão e de statement
        Connection con;
        Statement stmt;
        String query = "select name , id from sysobjects";

        // Verificando se o driver JDBC está instalado e pode ser utilizado
        try {
            Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");

        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }

        try {
            // Abrindo a conexão com o servidor MAURO, login sa e sem senha
            con = DriverManager.getConnection("jdbc:microsoft:sqlserver://shelton.sor.ufscar.br:3306", "labbd11", "hvi2011");
            System.out.println("CONECTOU!");
            /*
            stmt = con.createStatement();
            // Criando a instrução a partir do SELECT que  está dentro da variável query
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("Lista de linhas da tabela sysobjects:");

            // Fazendo um loop para mostrar tudo o que foi retornado do banco
            while (rs.next()) {

                // Obtendo o campo name em um string
                String s = rs.getString("name");
                // Obtendo o campo id em um inteiro
                int i = rs.getInt("id");
                System.out.println(s + "   " + i);
            }
            */
            //Fechamdno a instrução e a conexão
//            stmt.close();
            con.close();

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
}
