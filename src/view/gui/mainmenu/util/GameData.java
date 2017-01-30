package view.gui.mainmenu.util;

/**
 * The class storing the required data of the game.
 * @author Ahmed Walid
 */
public class GameData {

	/**
	 * The difficulty of the game, easy, normal or hard.
	 */
	private String gameDifficulty = "";

	/**
	 * Sets the difficulty level of the game.
	 * @param gameDifficulty Game difficulty
	 */
	public void setGameDifficulty(final String gameDifficulty) {
		this.gameDifficulty = gameDifficulty;
	}

	/**
	 * Return the difficulty of the game.
	 * @return game difficulty
	 */
	public String getGameDifficulty() {
		return this.gameDifficulty;
	}
}
