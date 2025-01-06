#version 460 core

layout (binding = 0) uniform sampler2D texSampler;

in vec2 texCoords;

out vec4 color;

void main() {
    vec4 texResult = texture(texSampler, texCoords);

    color = texResult * vec4(1.0F, 1.0F, 1.0F, 1.0F);
}