#version 450

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

in vec4 gl_FragCoord;

layout(location = 1) in vec3 normal;
layout(location = 2) in vec2 uv;
layout(location = 3) in vec3 tangent;
layout(location = 4) in vec3 bitangent;

layout(location = 0) out vec4 outAlbedo;
layout(location = 1) out vec4 outNormal;


const int MAX_STEPS = 64;
const float MIN_STEP_SIZE = 0.01f;
const float MAX_STEP_SIZE = 100.0f;
const float NORMAL_EPSILON = 0.01f;


// SHAPES
float sphereSDF(vec3 p, float radius) {
    return length(p) - radius;
}


// SCENE
float sceneSDF(vec3 p) {
    return sphereSDF(p, 1.0f);
}

void main() {

    // TODO: Non-static frame resolution
    const vec2 screenPosition = (gl_FragCoord.xy - vec2(640 / 2, 480 / 2)) / 640.0;

    const vec4 rayOrigin = cameraWorldMatrix * vec4(0, 0, 0, 1);
    const vec4 rayForward = cameraWorldMatrix * vec4(screenPosition, -1, 1);
    const vec4 rayDirection = rayForward - rayOrigin;

    for (float f = gl_FragCoord.z / gl_FragCoord.w; f < MAX_STEPS; f += MIN_STEP_SIZE) {

        const vec3 position = rayOrigin.xyz + rayDirection.xyz * f;
        const float distanceToScene = sceneSDF(position);

        if (distanceToScene <= 0.0) {

            float x = sceneSDF(position + vec3(NORMAL_EPSILON, 0, 0));
            float y = sceneSDF(position + vec3(0, NORMAL_EPSILON, 0));
            float z = sceneSDF(position + vec3(0, 0, NORMAL_EPSILON));

            outAlbedo = vec4(1, 1, 1, 1);
            outNormal = vec4((vec3(x, y, z) - position) / NORMAL_EPSILON, 1);

            outAlbedo = outNormal;

            return;
        }

        f += distanceToScene;

    }

    outAlbedo = vec4(0.125, 0.125, 0.125, 1);

}
