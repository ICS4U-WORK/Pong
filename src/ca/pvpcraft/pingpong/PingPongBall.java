package ca.pvpcraft.pingpong;

import java.util.Random;

/**
 * Ball instance including ball position and Direction
 * @author macdja38
 *
 */
public class PingPongBall {
	double x=50;
	double y=100;
	double xv=2;
	double yv=-1;
	Random rand = new Random();
	public PingPongBall() {
		
	}
	/**
	 * advance the ball one tick
	 * @param height of the field
	 * @param width of the field
	 */
	public void tick(double height, double width) {
		y+=yv;
		x+=xv;
		if((y-10)<0 || (y+10)>height) {
			yv=yv*-1;
		}
	}
	/**
	 * Tell the ball to go left.
	 */
	public void setDirL(){
		xv = Math.abs(xv)*-1;
	}
	/**
	 * @return Is the ball moving Left?
	 */
	public boolean isLeft() {
		if(xv<0){
			return true;
		}
		return false;
	}
	/**
	 * Tell the ball to go right.
	 */
	public void setDirR(){
		xv = Math.abs(xv);
	}
	/**
	 * @return Is the ball moving Right?
	 */
	public boolean isRight() {
		if(xv>0){
			return true;
		}
		return false;
	}
	/**
	 * Reset the coordinates of the ball to the field center and give it a random y velocity
	 * @param height
	 * @param width
	 */
	public void reset(double height, double width) {
		x=width/2;
		y=height/2;
		yv=rand.nextDouble()*2-1;
	}
	public double getY() {
		return y;
	}
	public double getX() {
		return x;
	}
	public double getYV() {
		return yv;
	}
	public void setYV(double yv) {
		this.yv = yv;
	}
	/**
	 * apply the some of the velocity of the paddle to the ping pong ball.
	 * @param paddle
	 */
	public void applyPaddle(PingPongPaddle paddle) {
		if(paddle.isUp()) {
			yv-=0.1;
		}
		if(paddle.isDown()) {
			yv+=0.1;
		}
	}
}
