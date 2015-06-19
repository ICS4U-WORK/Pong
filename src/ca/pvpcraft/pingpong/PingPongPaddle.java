package ca.pvpcraft.pingpong;

public class PingPongPaddle {
	double y=100;
	double yv=0;
	public PingPongPaddle() {
		
	}
	public PingPongPaddle(double height) {
		y = height;
	}

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

	public void reset(double height) {
		y=height/2-100;
		yv=0;
	}
	public int getY() {
		return (int)y;
	}
	public void setYV(double d) {
		yv=d;
	}
	public double getYV() {
		return yv;
	}
	public boolean isUp() {
		if(yv<0){
			return true;
		}
		return false;
	}
	public boolean isDown() {
		if(yv>0){
			return true;
		}
		return false;
	}

}
