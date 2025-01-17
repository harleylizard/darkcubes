#version 460 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec2 texture;

layout (binding = 0, column_major) uniform matrices {
    mat4 projection;
    mat4 pose;
};

out gl_PerVertex {
    vec4 gl_Position;
};

out vec2 texCoords;

void main() {
    gl_Position = projection * pose * position;

    texCoords = texture;
}