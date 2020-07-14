#version 450

// Uniform Buffers
layout(std140, binding = 4) uniform programState {
    float delta;
    double uptime;
    ivec4 rect;
};

layout(std140, binding = 0) uniform cameraTransform {
    mat4 cameraLocalMatrix;
    mat4 cameraWorldMatrix;
};

layout(std140, binding = 1) uniform cameraData {
    mat4 cameraProjection;
};

layout(std140, binding = 2) uniform objectTransform {
    mat4 objectLocalMatrix;
    mat4 objectWorldMatrix;
};


// Inputs
layout(location = 0) in vec4 in_position;
layout(location = 1) in vec4 in_normal;
layout(location = 2) in vec2 in_uv;
layout(location = 3) in vec4 in_tangent;


// Outputs
layout(location = 0) out gl_PerVertex { vec4 gl_Position; };
layout(location = 1) out vec3 normal;
layout(location = 2) out vec2 uv;
layout(location = 3) out vec3 tangent;
layout(location = 4) out vec3 bitangent;


void main() {
    gl_Position = cameraProjection * inverse(cameraWorldMatrix) * objectWorldMatrix * in_position;
    normal = (objectWorldMatrix * in_normal).xyz;
    uv = in_uv;
    tangent = (objectWorldMatrix * in_tangent).xyz;
    bitangent = normalize(cross(tangent, normal));
}