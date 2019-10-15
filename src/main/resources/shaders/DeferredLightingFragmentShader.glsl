#version 450
#extension GL_ARB_bindless_texture : require

layout(std140, binding = 0) uniform objectData {
    vec4 diffuseColor;
    sampler2D diffuseMap;
    sampler2D normalMap;
};

layout(location = 1) out vec4 uv;

const vec4 lightDirection = vec4(1, 0, 0, 1);

void main() {

}
