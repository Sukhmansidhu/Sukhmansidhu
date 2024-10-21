import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

// Snake class using LinkedList
class Snake {
    private LinkedList<Point> body;  // LinkedList for snake body parts
    private Direction currentDirection;

    public Snake(Point start) {
        body = new LinkedList<>();
        body.add(start);
        currentDirection = Direction.RIGHT;  // Initial direction
    }

    public LinkedList<Point> getBody() {
        return body;
    }

    public Direction getDirection() {
        return currentDirection;
    }

    public void setDirection(Direction direction) {
        // Prevent the snake from reversing
        if ((currentDirection == Direction.UP && direction != Direction.DOWN) ||
            (currentDirection == Direction.DOWN && direction != Direction.UP) ||
            (currentDirection == Direction.LEFT && direction != Direction.RIGHT) ||
            (currentDirection == Direction.RIGHT && direction != Direction.LEFT)) {
            currentDirection = direction;
        }
    }

    public void move() {
        Point head = body.getFirst();
        Point newHead = null;

        switch (currentDirection) {
            case UP:
                newHead = new Point(head.x, head.y - 1);
                break;
            case DOWN:
                newHead = new Point(head.x, head.y + 1);
                break;
            case LEFT:
                newHead = new Point(head.x - 1, head.y);
                break;
            case RIGHT:
                newHead = new Point(head.x + 1, head.y);
                break;
        }

        // Add new head to the front of the snake
        body.addFirst(newHead);
        body.removeLast();  // Remove the last part of the snake (tail)
    }

    public void grow() {
        Point tail = body.getLast(); // Get the last segment of the snake
        Point newTail = new Point(tail);
    
        // Add the new tail part to the snake
        body.addLast(newTail);  // Adds the new segment to the tail position
    }
    

    public boolean checkCollision() {
        Point head = body.getFirst();
        // Check if the snake runs into itself
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).equals(head)) {
                return true;
            }
        }
        return false;
    }
}

// Food class
class Food {
    private Point position;

    public Food() {
        generateNewPosition();
    }

    public Point getPosition() {
        return position;
    }

    public void generateNewPosition() {
        Random rand = new Random();
        position = new Point(rand.nextInt(20), rand.nextInt(20));  // Random 20x20 grid
    }
}

// Enum for directions
enum Direction {
    UP, DOWN, LEFT, RIGHT;
}

// Board class
class Board extends JPanel implements ActionListener {
    private static final int TILE_SIZE = 25;  // Size of each grid square
    private static final int BOARD_WIDTH = 20;
    private static final int BOARD_HEIGHT = 20;

    private Timer timer;
    private Snake snake;
    private Food food;

    public Board() {
        setPreferredSize(new Dimension(BOARD_WIDTH * TILE_SIZE, BOARD_HEIGHT * TILE_SIZE));
        setBackground(Color.BLACK);

        snake = new Snake(new Point(BOARD_WIDTH / 2, BOARD_HEIGHT / 2));  // Start snake in the middle
        food = new Food();

        timer = new Timer(150, this);
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        snake.setDirection(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        snake.setDirection(Direction.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        snake.setDirection(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        snake.setDirection(Direction.RIGHT);
                        break;
                }
            }
        });
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSnake(g);
        drawFood(g);
    }

    private void drawSnake(Graphics g) {
        g.setColor(Color.GREEN);
        for (Point part : snake.getBody()) {
            g.fillRect(part.x * TILE_SIZE, part.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }

    private void drawFood(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(food.getPosition().x * TILE_SIZE, food.getPosition().y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
        
        // Check if the snake eats the food
        if (snake.getBody().getFirst().equals(food.getPosition())) {
            snake.grow();
            food.generateNewPosition();
        }

        // Check if the snake collides with itself or the walls
        if (checkCollisionWithWalls() || snake.checkCollision()) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        repaint();
    }

    private boolean checkCollisionWithWalls() {
        Point head = snake.getBody().getFirst();
        return head.x < 0 || head.x >= BOARD_WIDTH || head.y < 0 || head.y >= BOARD_HEIGHT;
    }
}

// Main class for the game
public class SnakeGame extends JFrame {
    public SnakeGame() {
        setTitle("Snake Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(new Board());
        pack();
        setLocationRelativeTo(null);  // Center the window
        setVisible(true);
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}
