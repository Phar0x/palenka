#version 450 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec2 tc;

uniform mat4 pr_matrix; //projection matrix
uniform mat4 vw_matrix = mat4(1.0); //view matrix
uniform mat4 ml_matrix; //model matrix

out DATA
{
    vec2 tc;
} vs_out; // texture out to frag shader


void main()
{
    gl_Position = pr_matrix * vw_matrix * ml_matrix * position;
    vs_out.tc = tc;
}
