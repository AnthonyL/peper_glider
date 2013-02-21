package ter.utils;

public class HighScore {

	int timePlayer;
	int highScore;
	
	public HighScore(){
		highScore=0;
	}

	public HighScore(int highScore){
		this.highScore=highScore;
	}
	
	public void addTime (int time) {
		setTimePlayer(getTimePlayer()+time);
	}
	
	public void resetTimePlayer() {
		setTimePlayer(0);
	}
	
	public void resetHighScore() {
		setHighScore(0);
	}
	
	public void compareHight(){
		if (timePlayer>highScore){
			setHighScore(timePlayer);
		}
	}
		
	public int getTimePlayer() {
		return timePlayer;
	}
	
	public void setTimePlayer(int timePlayer) {
		this.timePlayer = timePlayer;
	}
	
	public int getHighScore() {
		return highScore;
	}
	
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
}
