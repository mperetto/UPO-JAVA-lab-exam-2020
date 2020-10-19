package upo.battleship.peretto;

public class BattleshipLauncher {
	
	public static void main(String[] args) {
		
		SettingsView settingsPartita = new SettingsView();
		SettingsController settingsController = new SettingsController(settingsPartita);
	}
	
}
