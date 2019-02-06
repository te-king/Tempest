#version 450
#extension GL_ARB_bindless_texture : require
#extension GL_ARB_gpu_shader_int64 : require


layout(std140, binding = 3) uniform objectData {
    vec4 diffuseColor;
    sampler2D diffuseMap;
    sampler2D normalMap;
};


layout(location = 1) in vec3 normal;
layout(location = 2) in vec2 uv;
layout(location = 3) in vec3 tangent;
layout(location = 4) in vec3 bitangent;


layout(location = 0) out vec4 outAlbedo;
layout(location = 1) out vec4 outNormal;


void main() {
    mat3 tangentToWorldspace = mat3(tangent,
                                    bitangent,
                                    normal);

    // TODO: Convert to non-branching code
    if (uint64_t(diffuseMap) > 0)
        outAlbedo = texture(diffuseMap, uv);
    else
        outAlbedo = diffuseColor;

    outNormal = vec4(tangentToWorldspace * (texture(normalMap, uv).xyz * 2.0 - 1.0), 1f);
}
