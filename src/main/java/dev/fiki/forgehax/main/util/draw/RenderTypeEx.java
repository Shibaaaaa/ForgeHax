package dev.fiki.forgehax.main.util.draw;

import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.OptionalDouble;

public class RenderTypeEx extends RenderType {
  private static final RenderType GL_LINES = RenderType.makeType("fh_lines",
      DefaultVertexFormats.POSITION_COLOR, GL11.GL_LINES, 256,
      RenderType.State.getBuilder()
          .line(new RenderState.LineState(OptionalDouble.of(1.f)))
          .cull(RenderState.CULL_DISABLED)
          .depthTest(DEPTH_ALWAYS)
          .transparency(RenderState.TRANSLUCENT_TRANSPARENCY)
          .fog(RenderState.NO_FOG)
          .build(false)
  );

  private static final RenderType GL_LINE_LOOP = RenderType.makeType("fh_line_loop",
      DefaultVertexFormats.POSITION_COLOR, GL11.GL_LINE_LOOP, 256,
      RenderType.State.getBuilder()
          .line(new RenderState.LineState(OptionalDouble.empty()))
          .transparency(RenderState.TRANSLUCENT_TRANSPARENCY)
          .fog(RenderState.NO_FOG)
          .build(false)
  );

  private static final RenderType GL_TRIANGLES = RenderType.makeType("fh_triangles",
      DefaultVertexFormats.POSITION_COLOR, GL11.GL_TRIANGLES, 256,
      RenderType.State.getBuilder()
          .transparency(RenderState.TRANSLUCENT_TRANSPARENCY)
          .fog(RenderState.NO_FOG)
          .build(false)
  );

  private static final RenderType GL_QUADS = RenderType.makeType("fh_quads",
      DefaultVertexFormats.POSITION_COLOR, GL11.GL_QUADS, 256,
      RenderType.State.getBuilder()
          .transparency(RenderState.TRANSLUCENT_TRANSPARENCY)
          .fog(RenderState.NO_FOG)
          .build(false)
  );

  private static final RenderType BLOCK_TRANSLUCENT_CULL = entityTranslucentCull(PlayerContainer.LOCATION_BLOCKS_TEXTURE);

  private static final RenderType BLOCK_CUTOUT = entityCutout(PlayerContainer.LOCATION_BLOCKS_TEXTURE);

  public static RenderType glLines() {
    return GL_LINES;
  }

  public static RenderType glLineLoop() {
    return GL_LINE_LOOP;
  }

  public static RenderType glTriangle() {
    return GL_TRIANGLES;
  }

  public static RenderType glQuads() {
    return GL_QUADS;
  }

  public static RenderType blockTranslucentCull() {
    return BLOCK_TRANSLUCENT_CULL;
  }

  public static RenderType blockCutout() {
    return BLOCK_CUTOUT;
  }

  public static RenderType entityTranslucentCull(ResourceLocation texture) {
    RenderType.State state = RenderType.State.getBuilder()
        .texture(new RenderState.TextureState(texture, false, false))
        .transparency(TRANSLUCENT_TRANSPARENCY)
        .diffuseLighting(DIFFUSE_LIGHTING_DISABLED)
        .alpha(DEFAULT_ALPHA)
        .lightmap(LIGHTMAP_DISABLED)
        .overlay(OVERLAY_ENABLED)
        .build(true);
    return makeType("fh_entity_translucent_cull", DefaultVertexFormats.ENTITY,
        GL11.GL_QUADS, 256, true, true, state);
  }

  public static RenderType entityCutout(ResourceLocation texture) {
    RenderType.State state = RenderType.State.getBuilder()
        .texture(new RenderState.TextureState(texture, false, false))
        .transparency(NO_TRANSPARENCY)
        .diffuseLighting(DIFFUSE_LIGHTING_DISABLED)
        .alpha(DEFAULT_ALPHA)
        .lightmap(LIGHTMAP_DISABLED)
        .overlay(OVERLAY_ENABLED)
        .build(true);
    return makeType("fh_entity_cutout", DefaultVertexFormats.ENTITY,
        GL11.GL_QUADS, 256, true, false, state);
  }

  private RenderTypeEx(String id,
      VertexFormat vertexFormat,
      int glMode, int bufferSize,
      boolean useDelegate, boolean needsSorting,
      Runnable enableTask, Runnable disableTask) {
    super(id, vertexFormat, glMode, bufferSize, useDelegate, needsSorting, enableTask, disableTask);
  }
}
