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


float sphereSDF(vec3 p, float radius) {
    return length(p) - radius;
}

float sceneSDF(vec3 p) {
    return sphereSDF(p, 1.0f);
}

void main() {

    // TODO: Non-static frame resolution
    const vec2 screenPosition = (gl_FragCoord.xy - vec2(640 / 2, 480 / 2)) / 640.0;

    const vec4 rayOrigin = cameraWorldMatrix * vec4(0, 0, 0, 1);
    const vec4 rayForward = cameraWorldMatrix * vec4(screenPosition, -1, 1);
    const vec4 rayDirection = rayForward - rayOrigin;

    float distanceTraveled = 0.0f;

    for (int i = 0; i < 100; ++i) {

        const float distanceToScene = sceneSDF(rayOrigin.xyz + rayDirection.xyz * distanceTraveled);

        if (distanceToScene <= 0.1f) {
            outAlbedo = vec4(0, 1, 0, 1);
            return;
        }

        distanceTraveled += distanceToScene;

    }

    outAlbedo = vec4(1, 0, 0, 1);

}
