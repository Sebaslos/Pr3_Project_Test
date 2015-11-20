package de.hsh.prog.gogodie.game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private BufferedImage spriteSheet;
	private HashMap<AnimationID, ArrayList<Integer>> animations;
    private ArrayList<Integer> currentAnimation;

    private boolean animationIsPlaying = false;
    private boolean animationLoops = false;

    private int currentFrameIndex, ticks;
    private int frameWidth, frameHeight, frameDuration;

    private int gridWidth;
	
	public SpriteSheet(String path, int frameWidth, int frameHeight, int frameDuration) {
		try {
			spriteSheet = ImageIO.read(this.getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (this.spriteSheet.getWidth() % frameWidth != 0 || this.spriteSheet.getHeight() % frameHeight != 0) {
            throw new IllegalArgumentException("Frames don't distribute equally over spritesheet.");
        }

        gridWidth = this.spriteSheet.getWidth() / frameWidth;

        this.animations = new HashMap<>();
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameDuration = frameDuration;
	}
	
	public void addAnimation(AnimationID id, Integer ... frameOrder) {
        ArrayList<Integer> frames = new ArrayList<>(frameOrder.length);
        Collections.addAll(frames, frameOrder);
        animations.put(id, frames);
    }
	
	public void playAnimation(AnimationID id, boolean loop) {
        ArrayList<Integer> animation = animations.get(id);
        if (currentAnimation != animation) {
            currentAnimation = animations.get(id);
            animationIsPlaying = true;
            animationLoops = loop;
            ticks = 0;
            currentFrameIndex = 0;
        }
    }

    public void update() {
        if (animationIsPlaying) {
            ticks++;

            if (ticks > frameDuration) {
                ticks = 0;

                if (++currentFrameIndex >= currentAnimation.size()) {
                    currentFrameIndex = 0;
                    if (!animationLoops) {
                        animationIsPlaying = false;
                    }
                }
            }
        }
    }

    public BufferedImage getFrame(int index){
    	int width = (index % gridWidth) * frameWidth;
        int height = (index / gridWidth) * frameHeight;
        return spriteSheet.getSubimage(width, height, frameWidth, frameHeight);
    }
    
    public BufferedImage getCurrentFrame() {
    	System.out.println(currentAnimation);
        int index = currentAnimation.get(currentFrameIndex);
        return getFrame(index);
    }
	
}
