#version 450

layout(location = 0) out gl_PerVertex { vec4 gl_Position; };
layout(location = 1) out vec2 uv;

void main()
{
    float x = -1.0 + float((gl_VertexID & 1) << 2);
    float y = -1.0 + float((gl_VertexID & 2) << 1);
    gl_Position = vec4(x, y, 0, 1);
    uv = vec2((x + 1.0) * 0.5, (y + 1.0) * 0.5);
}