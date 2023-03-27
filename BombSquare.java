import javax.swing.JOptionPane;

public class BombSquare extends GameSquare
{
    private GameBoard board;                            // Object reference to the GameBoard this square is part of.
    private boolean hasBomb;                            // True if this square contains a bomb. False otherwise.
    //variable discovered to see if a square is discovered true or false
    private boolean discovered;
    //variable flagged to see if a square has flagged
    private boolean flagged;
	public static final int MINE_PROBABILITY = 10;
	//initial variables for each direction
	BombSquare south, north, east, west, south_east, south_west, north_east, north_west;
	
	public BombSquare(int x, int y, GameBoard boards)
	{
		super(x, y, "images/blank.png");

        this.board = boards;
        this.hasBomb = ((int) (Math.random() * MINE_PROBABILITY)) == 0;
    }
	//method check surrounding bombs
	public int surroundbomb() {
		//coordinates for each direction variables
		south = (BombSquare)board.getSquareAt(getXLocation(), (getYLocation()-1));
		north = (BombSquare)board.getSquareAt(getXLocation(), (getYLocation()+1));
		east = (BombSquare)board.getSquareAt((getXLocation()-1), (getYLocation()));
		west = (BombSquare)board.getSquareAt((getXLocation()+1), (getYLocation()));
		south_east = (BombSquare)board.getSquareAt((getXLocation()-1), (getYLocation()-1));
		south_west = (BombSquare)board.getSquareAt((getXLocation()+1), (getYLocation()-1));
		north_east = (BombSquare)board.getSquareAt((getXLocation()-1), (getYLocation()+1));
		north_west = (BombSquare)board.getSquareAt((getXLocation()+1), (getYLocation()+1));
		int counter = 0;
		if(south != null && south.isbomb() == true) {
			counter++;
		}
		if(north != null && north.isbomb() == true) {
			counter++;
		}
		if(east != null && east.isbomb() == true) {
			counter++;
		}
		if(west != null && west.isbomb() == true) {
			counter++;
		}
		if(south_east != null && south_east.isbomb() == true) {
			counter++;
		}
		if(south_west != null &&south_west.isbomb() == true) {
			counter++;
		}
		if(north_east != null && north_east.isbomb() == true) {
			counter++;
		}
		if(north_west != null && north_west.isbomb() == true) {
			counter++;
		}
		return counter;
	}
	//left-click to reveal square
	@Override
	public void leftClicked() {
		if(hasBomb == false && discovered == false) {
			
			//switch cases for displaying numbers show surrounding bombs
			switch(surroundbomb()) {
			case 0:
				setImage("images/0.png");
				discovered = true;
				//Recursive of left clicked of each direction, reveal and set to 0.png if the bomb counts of the adjacent is 0
				if(south != null && south.surroundbomb() == 0) {
					south.leftClicked();
				}
				if(north != null && north.surroundbomb() == 0) {
					north.leftClicked();
				}
				if(east != null && east.surroundbomb() == 0) {
					east.leftClicked();
				}
				if(west != null && west.surroundbomb() == 0) {
					west.leftClicked();
				}
				if(south_east != null && south_east.surroundbomb() == 0) {
					south_east.leftClicked();
				}
				if(south_west != null && south_west.surroundbomb() == 0) {
					south_west.leftClicked();
				}
				if(north_east != null && north_east.surroundbomb() == 0) {
					north_east.leftClicked();
				}
				if(north_west != null && north_west.surroundbomb() == 0) {
					north_west.leftClicked();
				}
			break;
			case 1: 
				setImage("images/1.png");
				discovered = true;
				break;
			case 2:
				setImage("images/2.png");
				discovered = true;
				break;
			case 3:
				setImage("images/3.png");
				discovered = true;
				break;
			case 4:
				setImage("images/4.png");
				discovered = true;
				break;
			case 5: 
				setImage("images/5.png");
				discovered = true;
				break;
			case 6:
				setImage("images/6.png");
				discovered = true;
				break;
			case 7:
				setImage("images/7.png");
				discovered = true;
				break;
			case 8:
				setImage("images/8.png");
				discovered = true;
				break;
			default:
				break;
			}
		}
		//Set to bomb.png if hasBomb is true
		else if (hasBomb == true && discovered == false){
			setImage("images/bomb.png");
			discovered = true;
			JOptionPane.showMessageDialog(board,"You Died...\nStepped on a Bomb", "Game Result", JOptionPane.PLAIN_MESSAGE );
			//System.exit(0);
		}
	}
	//display a flag if square not revealed, second click remove
	@Override
	public void rightClicked() {
		flagged = !flagged;
		if(discovered == false) {
		setImage(flagged ? "images/flag.png" : "images/blank.png" );
		} 
	}
	//setters and getters
	public boolean isbomb() {
		return hasBomb;
	}
	public boolean isDiscovered() {
		return discovered;
	}
	public void setDiscovered(boolean d) {
		this.discovered = d;
	}
	public boolean isFlagged() {
		return flagged;
	}
	public void setflagged(boolean f) {
		this.flagged = f;
	}	
}