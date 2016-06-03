#ifdef GL_ES
precision mediump float;
#endif


varying vec2 v_texCoord;
uniform sampler2D u_texture;

uniform vec4 bgColor;
uniform vec4 fgColor;

float median(float r, float g, float b) {
    return max(min(r, g), min(max(r, g), b));
}


void main() {
    vec3 sam = texture(u_texture, v_texCoord).rgb;
    float sigDist = median(sam.r, sam.g, sam.b) - 0.5;
    float opacity = clamp(sigDist / fwidth(sigDist) + 0.5, 0.0, 1.0);


    gl_FragColor = mix(bgColor, fgColor, opacity);
}