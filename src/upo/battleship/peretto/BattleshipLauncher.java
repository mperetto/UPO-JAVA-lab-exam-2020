package upo.battleship.peretto;

/**
 * La classe si occupa di lanciare l'applicazione inizializzando tutte le componenti necessarie.
 * */
public class BattleshipLauncher {
	
	public static void main(String[] args) {
		
		SettingsView settingsPartita = new SettingsView();
		SettingsController settingsController = new SettingsController(settingsPartita);
	}
	
}
