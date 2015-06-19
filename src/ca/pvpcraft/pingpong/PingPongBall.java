package ca.pvpcraft.pingpong;

import java.util.Random;

public class PingPongBall {
	double x=50;
	double y=100;
	double xv=2;
	double yv=-1;
	Random rand = new Random();
	public PingPongBall() {
		
	}
	public void tick(double height, double width) {
		y+=yv;
		x+=xv;
		if((y-10)<0 || (y+10)>height) {
			yv=yv*-1;
		}
	}
	public void setDirL(){
		xv = Math.abs(xv)*-1;
	}
	public boolean isLeft() {
		if(xv<0){
			return true;
		}
		return false;
	}
	public void setDirR(){
		xv = Math.abs(xv);
	}
	public boolean isRight() {
		if(xv>0){
			return true;
		}
		return false;
	}
	public void reset(double height, double width) {
		x=width/2;
		y=height/2;
		//yv=rand.nextDouble()*2-1;
		yv=0;
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
	public void applyPaddle(PingPongPaddle paddle) {
		if(paddle.isUp()) {
			yv-=0.1;
		}
		if(paddle.isDown()) {
			yv+=0.1;
		}
	}
}
