
package dao;

import factory.Conexao;
import gui.CadastroUsuario;
import gui.Login;
import gui.MenuMedicos;
import gui.MenuPrincipal;
import gui.MenuRecepcionista;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;


public class LoginDAO {
    
    private Connection connection;
    private int id;
    private String username;
    private String senha;
    private String perfil;
    private Login gui;
    private CadastroUsuario cu;
    
    
     
     
     
     public LoginDAO(Login gui){ 
        this.connection = new Conexao().getConnection();
        this.gui = gui;
    
     }
    
     
     public void logar(){
       
         
         String username = gui.getTxtUsername().getText();
         String senha = gui.getTxtSenha().getText();
         
         try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        try (Connection conn = (Connection) 
                
                DriverManager.getConnection("jdbc:mysql://localhost:3306/clinica?useTimezone=true&serverTimezone=UTC","root","12345"); 
                Statement stmt = (Statement) conn.createStatement()) {

            String query = "select * from usuarios where username = '" + username +"' and senha = '" + senha + "'";

                try (ResultSet rs = stmt.executeQuery(query)) {
                    if (rs.next()) {
                        
                       String perfil = rs.getString("perfil");
       
                        if("Administrador".equals(perfil)){
                           JOptionPane.showMessageDialog(null,"Conectado com sucesso");
                           MenuPrincipal menu = new MenuPrincipal();
                           menu.setVisible(true);
                           this.gui.dispose();
                        }
                        
                        else if("Recepcionista".equals(perfil)){
                           JOptionPane.showMessageDialog(null,"Conectado com sucesso");
                           MenuRecepcionista recepcao = new MenuRecepcionista();
                           recepcao.setVisible(true);
                           this.gui.dispose();
                        }
                        
                        else if("Médico".equals(perfil)){
                           JOptionPane.showMessageDialog(null,"Conectado com sucesso");
                           MenuMedicos med = new MenuMedicos();
                           med.setVisible(true);
                           this.gui.dispose();
                        }
        
                    }
                    

                    else {
                        JOptionPane.showMessageDialog(null,"Usuário e/ou senha incorretos.");
                        gui.getTxtUsername().setText("");
                        gui.getTxtSenha().setText("");
                        
                    }   
                }
        
                    //caso seja preciso mais tipos de acesso, copie e cole o código do IF para cada cargo

    
        }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        
   }
     
     
     
    
}
