package org.anddev.andengine.examples;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.scene.transition.FadeTransitionScene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;

/**
 * @author Nicolas Gramlich
 * @since 11:54:51 - 03.04.2010
 */
public class SpriteExample extends BaseExample {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final int CAMERA_WIDTH = 720;
	private static final int CAMERA_HEIGHT = 480;

	// ===========================================================
	// Fields
	// ===========================================================

	private Camera mCamera;
	private Texture mTexture;
	private TextureRegion mFaceTextureRegion;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public Engine onLoadEngine() {
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera));
	}

	@Override
	public void onLoadResources() {
		this.mTexture = new Texture(32, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mFaceTextureRegion = TextureRegionFactory.createFromAsset(this.mTexture, this, "gfx/face_box.png", 0, 0);

		this.mEngine.getTextureManager().loadTexture(this.mTexture);
	}

	@Override
	public Scene onLoadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		final Scene sceneA = new Scene(1);
		{
			sceneA.setBackground(new ColorBackground(0.09804f, 0.6274f, 0.8784f));
	
			/* Calculate the coordinates for the face, so its centered on the camera. */
			final int centerX = (CAMERA_WIDTH - this.mFaceTextureRegion.getWidth()) / 2 - 100;
			final int centerY = (CAMERA_HEIGHT - this.mFaceTextureRegion.getHeight()) / 2;
	
			/* Create the face and add it to the scene. */
			final Sprite face = new Sprite(centerX, centerY, this.mFaceTextureRegion);
			sceneA.getTopLayer().addEntity(face);
		}

		final Scene sceneB = new Scene(1);
		{
			sceneB.setBackground(new ColorBackground(0.09804f, 0.6274f, 0.8784f));
	
			/* Calculate the coordinates for the face, so its centered on the camera. */
			final int centerX = (CAMERA_WIDTH - this.mFaceTextureRegion.getWidth()) / 2 + 100;
			final int centerY = (CAMERA_HEIGHT - this.mFaceTextureRegion.getHeight()) / 2;
	
			/* Create the face and add it to the scene. */
			final Sprite face = new Sprite(centerX, centerY, this.mFaceTextureRegion);
			sceneB.getTopLayer().addEntity(face);
		}

		return new FadeTransitionScene(5, sceneA, sceneB, 1, 1, 1);
	}

	@Override
	public void onLoadComplete() {

	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
