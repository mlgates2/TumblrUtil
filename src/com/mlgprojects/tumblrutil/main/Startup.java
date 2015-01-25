package com.mlgprojects.tumblrutil.main;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.User;

public class Startup {
	private static final Logger logger = Logger.getLogger(Startup.class.getCanonicalName());
	private static Map<String,String> authentication = new HashMap<String,String>();
	
	public static void main(String[] args) { 
		authenticate();
	}

	private static void authenticate() {
		JTextField oAuthConsumerKeyField = new JTextField(20);
		JTextField oAuthConsumerSecretField = new JTextField(20);
		JTextField oAuthTokenField = new JTextField(20);
		JTextField oAuthTokenSecretField = new JTextField(20);
		JCheckBox rememberMeCheckbox = new JCheckBox("Remember Me");
		rememberMeCheckbox.setSelected(true);

		JPanel loginDialog = new JPanel();
		loginDialog.setLayout(new BoxLayout(loginDialog, BoxLayout.Y_AXIS));
		loginDialog.add(new JLabel("OAuth Consumer Key:"));
		loginDialog.add(oAuthConsumerKeyField);
		loginDialog.add(Box.createVerticalStrut(10));
		loginDialog.add(new JLabel("OAuth Consumer Secret:"));
		loginDialog.add(oAuthConsumerSecretField);
		loginDialog.add(Box.createVerticalStrut(10));
		loginDialog.add(new JLabel("OAuth Token:"));
		loginDialog.add(oAuthTokenField);
		loginDialog.add(Box.createVerticalStrut(10));
		loginDialog.add(new JLabel("OAuth Token Secret:"));
		loginDialog.add(oAuthTokenSecretField);
		loginDialog.add(Box.createVerticalStrut(10));
		loginDialog.add(rememberMeCheckbox);

		JOptionPane.showMessageDialog(null, loginDialog, "Login",JOptionPane.OK_OPTION);

		authentication.put("oAuthConsumerKey", oAuthConsumerKeyField.getText());
		authentication.put("oAuthConsumerSecret", oAuthConsumerSecretField.getText());
		authentication.put("oAuthToken", oAuthTokenField.getText());
		authentication.put("oAuthTokenSecret", oAuthTokenSecretField.getText());
		
		logger.log(Level.INFO, "Connecting to JumblrClient....");
		// Authenticate via OAuth
		JumblrClient client = new JumblrClient(authentication.get("oAuthConsumerKey"), authentication.get("oAuthConsumerSecret"));
		client.setToken(authentication.get("oAuthToken"), authentication.get("oAuthTokenSecret"));

		// Make the request
		User user = client.user();

		logger.log(Level.INFO, "Connected to JumblrClient....");
	}

}
