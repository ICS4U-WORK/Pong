package ca.pvpcraft.pingpong;

/**
 * Paddle instance including paddle position, direction and AI
 * @author macdja38
 *
 */
public class PingPongPaddle {
	double y=100;
	double yv=0;
	public PingPongPaddle() {
		
	}
	public PingPongPaddle(double height) {
		y = height;
	}
	/**
	 * advance the paddle one position
	 * @param height of the playing field
	 * @param AI enabled?
	 * @param paddleHeight
	 * @param BallY
	 */
	public void tick(double height, boolean AI, double paddleHeight, double BallY) {
		if(AI==false) {
			y+=yv;
		}
		else {
			/*if(BallY-(getY()+(paddleHeight/2))>0){
				
			}*/
			if(BallY-(getY()+(paddleHeight/2))-1>0) {
				y+=1;
			}
			if(BallY-(getY()+(paddleHeight/2))+1<0) {
				y-=1;
			}
		}
		if(y<-100){y=height;}
		if(y>height){y=-100;}
	}
	/**
	 * Reset paddle position and speed
	 * @param height
	 */
	public void reset(double height) {
		y=height/2-100;
		yv=0;
	}
	/**
	 * Get position
	 * @return
	 */
	public int getY() {
		return (int)y;
	}
	/**
	 * Set ball Y velocity
	 * @param d
	 */
	public void setYV(double d) {
		yv=d;
	}
	/**
	 * Get ball Y velocity
	 * @param d
	 */
	public double getYV() {
		return yv;
	}
	/**
	 * @return Is the paddle moving up?
	 */
	public boolean isUp() {
		if(yv<0){
			return true;
		}
		return false;
	}
	/**
	 * @return Is the paddle moving down?
	 */
	public boolean isDown() {
		if(yv>0){
			return true;
		}
		return false;
	}

}
