package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;

public class MSDFTestGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    TextureRegion textureRegion;

    ShaderProgram shaderProgram;

    float scale = 5;
    float rotation;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("a64.png");


        img.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


        textureRegion = new TextureRegion(img);
        shaderProgram = new ShaderProgram(Gdx.files.internal("shader/msdf.vs"), Gdx.files.internal("shader/msdf.fs"));

        if (!shaderProgram.isCompiled()) {
            System.out.println(shaderProgram.getLog());
        }

        batch.setShader(shaderProgram);


    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            rotation -= 1f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            rotation += 1f;
        }


        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            scale += .2f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            scale -= .2;
        }

        scale = MathUtils.clamp(scale, 0.5f, 40);


        batch.begin();

        shaderProgram.setUniformf("bgColor", 1.0f, 1.0f, 1.0f, 0.0f);
        shaderProgram.setUniformf("fgColor", 0.0f, 0.0f, 0.0f, 1.0f);

        batch.draw(textureRegion, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, img.getWidth() / 2f, img.getHeight() / 2f, img.getWidth(), img.getHeight(), scale, scale, rotation);
        batch.end();
    }

}
