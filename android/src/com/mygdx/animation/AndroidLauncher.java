package com.mygdx.animation;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.animation.animation;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useImmersiveMode = true; // Para ocupar toda la pantalla
		config.useAccelerometer = false; // Si no necesitas el acelerómetro
		config.useCompass = false; // Si no necesitas la brújula
		initialize(new animation(), config);
	}
}
