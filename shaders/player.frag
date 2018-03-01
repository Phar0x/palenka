#version 450 core

layout (location = 0) out vec4 color;
uniform vec3 col;

in DATA
{
    vec2 tc;
} fs_in;

uniform sampler2D tex;

void main()
{
    color = texture(tex, fs_in.tc);
}