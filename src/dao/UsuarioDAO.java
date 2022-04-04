
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

public class UsuarioDAO {
    
    private Connection connection;
    private int id;
    private String username;
    private String senha;
    private String perfil;
    private Login gui;
    private CadastroUsuario cu;
    
    
    public UsuarioDAO(){ 
        this.connection = new Conexao().getConnection();
        
    }
    
    
    public void inserir(Usuario usuario){
    
        String sql = "INSERT INTO usuarios(username, senha, perfil) "
                + "VALUES(?,?,?)";
        
       try{
           PreparedStatement stmt = connection.prepareStatement(sql);
           stmt.setString(1, usuario.getUsername());
           stmt.setString(2, usuario.getSenha());
           stmt.setString(3, usuario.getPerfil());
          
           stmt.execute();
           stmt.close();
           
           
           
       }
       catch(SQLException u){
           throw  new RuntimeException(u);
       } 
          
    }
     
    public Usuario consultar(Usuario usuario){
        
        Usuario consulta = new Usuario();
        String sql = "select *"
                + "from usuarios where id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,String.valueOf(usuario.getId()));
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                consulta.setUsername(rs.getString(2));
                consulta.setSenha(rs.getString(3));
                consulta.setPerfil(rs.getString(4));
            
            }
            else{
                consulta = null;
            }
            rs.close();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return(consulta);
    }
    
    
}
