package net.breakzone.tnttag.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;


public class MySQL {
	private Connection connection;
	public String host, database, username, password;
	public int port;

	public void onEnable() {
		mysqlSetup();
	}
	
	public void mysqlSetup(){
		host = "localhost";
		port = 3306;
		database = "tnttag";
		username = "root";
		password = "password";
	}
			
			public void openConnection() {
			    try {
			      if (this.connection != null && !this.connection.isClosed())
			        return; 
			      synchronized (this) {
			        if (this.connection != null && !this.connection.isClosed())
			          return; 
			        try {
			          Class.forName("com.mysql.jdbc.Driver");
			        } catch (ClassNotFoundException classNotFoundException) {
			          classNotFoundException.printStackTrace();
			        } 
			        this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + 
			            this.database + "?autoReconnect=true&useSSL=false", this.username, this.password);
				
				Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MYSQL CONNECTED");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}


	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}


	private void disconnect() {

	}

	private void execute()
	 {

	}
}

