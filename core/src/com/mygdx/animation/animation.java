package com.mygdx.animation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;



public class animation extends ApplicationAdapter {
	// Constant rows and columns of the sprite sheet
	private static final int FRAME_COLS = 6, FRAME_ROWS = 1;

	// Objects used
	public Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
	Texture sheet;
	Texture background;
	SpriteBatch batch;
	TextureRegion bgRegion;
	int posx = 100;
	int posy;
	int posx2;
	int posy2 = 90;
	Rectangle up, down, left, right, fire;
	final int IDLE=0, UP=1, DOWN=2, LEFT=3, RIGHT=4;

	// A variable for tracking elapsed time for the animation
	float stateTime;
	float lastSend=0;

	public animation() {

	}

	@Override
	public void create() {

		// Load the sprite sheet as a Texture
		sheet = new Texture(Gdx.files.internal("ragnarok_izquierda.PNG"));
		TextureRegion[][] tmp = TextureRegion.split(sheet,
				sheet.getWidth() / FRAME_COLS,
				sheet.getHeight() / FRAME_ROWS);
		TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation<TextureRegion>(0.100f, walkFrames);
		up = new Rectangle(0, 800*2/3, 600, 800/3);
		down = new Rectangle(0, 0, 600, 800/3);
		left = new Rectangle(0, 0, 600/3, 800);
		right = new Rectangle(600*2/3, 0, 700/3, 800);

		background = new Texture(Gdx.files.internal("background12.jpg"));
		background.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
		bgRegion = new TextureRegion(background);
		posx2 = 0;
		posy = 0;
		batch = new SpriteBatch();
		stateTime = 0f;
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime(); // Acumular tiempo transcurrido para la animación
		TextureRegion frame = walkAnimation.getKeyFrame(stateTime,true);

		// Calcular las coordenadas para la esquina inferior izquierda
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();

		// Ajustar el tamaño del muñeco (por ejemplo, multiplicar por 2)
		float scale = 3.0f;
		float animWidth = frame.getRegionWidth() * scale;
		float animHeight = frame.getRegionHeight() * scale;

		// Mover el personaje de izquierda a derecha
		posx += 4; // Incrementar la velocidad de movimiento

		// Si el personaje sale de la pantalla por la derecha, volver a colocarlo a la izquierda
		if (posx > screenWidth) {
			posx = (int) -animWidth;
		}

		// Dibujar la imagen de fondo ocupando toda la pantalla
		batch.begin();
		batch.draw(background, 0, 0, screenWidth, screenHeight); // Dibujar fondo que ocupe toda la pantalla
		batch.draw(frame, posx, 0, animWidth, animHeight); // Dibujar la animación escalada
		batch.end();
	}





	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() { // SpriteBatches and Textures must always be disposed
		batch.dispose();
		sheet.dispose();
	}

}
