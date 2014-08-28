package com.pauljoda.mobtools.rendering;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelInfuser extends ModelBase
{
  //fields
    ModelRenderer CentralBeam;
    ModelRenderer Base;
    ModelRenderer TopBase;
    ModelRenderer Table;
    ModelRenderer Rim1;
    ModelRenderer Rim2;
    ModelRenderer Rim3;
    ModelRenderer Rim4;
    ModelRenderer Brace1;
    ModelRenderer Brace2;
    ModelRenderer Brace3;
    ModelRenderer Brace4;
  
  public ModelInfuser()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      CentralBeam = new ModelRenderer(this, 0, 0);
      CentralBeam.addBox(0F, 0F, 0F, 6, 9, 6);
      CentralBeam.setRotationPoint(-3F, 14F, -3F);
      CentralBeam.setTextureSize(64, 64);
      CentralBeam.mirror = true;
      setRotation(CentralBeam, 0F, 0F, 0F);
      Base = new ModelRenderer(this, 24, 0);
      Base.addBox(0F, 0F, 0F, 8, 1, 8);
      Base.setRotationPoint(-4F, 23F, -4F);
      Base.setTextureSize(64, 64);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      TopBase = new ModelRenderer(this, 24, 0);
      TopBase.addBox(0F, 0F, 0F, 8, 1, 8);
      TopBase.setRotationPoint(-4F, 13F, -4F);
      TopBase.setTextureSize(64, 64);
      TopBase.mirror = true;
      setRotation(TopBase, 0F, 0F, 0F);
      Table = new ModelRenderer(this, 0, 15);
      Table.addBox(0F, 0F, 0F, 16, 4, 16);
      Table.setRotationPoint(-8F, 9F, -8F);
      Table.setTextureSize(64, 64);
      Table.mirror = true;
      setRotation(Table, 0F, 0F, 0F);
      Rim1 = new ModelRenderer(this, 0, 35);
      Rim1.addBox(0F, 0F, 0F, 1, 1, 16);
      Rim1.setRotationPoint(7F, 8F, -8F);
      Rim1.setTextureSize(64, 64);
      Rim1.mirror = true;
      setRotation(Rim1, 0F, 0F, 0F);
      Rim2 = new ModelRenderer(this, 0, 52);
      Rim2.addBox(0F, 0F, 0F, 16, 1, 1);
      Rim2.setRotationPoint(-8F, 8F, 7F);
      Rim2.setTextureSize(64, 64);
      Rim2.mirror = true;
      setRotation(Rim2, 0F, 0F, 0F);
      Rim3 = new ModelRenderer(this, 0, 52);
      Rim3.addBox(0F, 0F, 0F, 16, 1, 1);
      Rim3.setRotationPoint(-8F, 8F, -8F);
      Rim3.setTextureSize(64, 64);
      Rim3.mirror = true;
      setRotation(Rim3, 0.0174533F, 0F, 0F);
      Rim4 = new ModelRenderer(this, 0, 35);
      Rim4.addBox(0F, 0F, 0F, 1, 1, 16);
      Rim4.setRotationPoint(-8F, 8F, -8F);
      Rim4.setTextureSize(64, 64);
      Rim4.mirror = true;
      setRotation(Rim4, 0F, 0F, 0F);
      Brace1 = new ModelRenderer(this, 56, 0);
      Brace1.addBox(0F, 0F, 0F, 1, 9, 1);
      Brace1.setRotationPoint(6F, 0F, 7F);
      Brace1.setTextureSize(64, 64);
      Brace1.mirror = true;
      setRotation(Brace1, 0F, 0.7853982F, 0F);
      Brace2 = new ModelRenderer(this, 56, 0);
      Brace2.addBox(0F, 0F, 0F, 1, 9, 1);
      Brace2.setRotationPoint(6F, 0F, -7F);
      Brace2.setTextureSize(64, 64);
      Brace2.mirror = true;
      setRotation(Brace2, 0F, 0.7853982F, 0F);
      Brace3 = new ModelRenderer(this, 56, 0);
      Brace3.addBox(0F, 0F, 0F, 1, 9, 1);
      Brace3.setRotationPoint(-8F, 0F, -7F);
      Brace3.setTextureSize(64, 64);
      Brace3.mirror = true;
      setRotation(Brace3, 0F, 0.7853982F, 0F);
      Brace4 = new ModelRenderer(this, 56, 0);
      Brace4.addBox(0F, 0F, 0F, 1, 9, 1);
      Brace4.setRotationPoint(-7F, 0F, 7F);
      Brace4.setTextureSize(64, 64);
      Brace4.mirror = true;
      setRotation(Brace4, 0F, 0.7853982F, 0F);
  }
  
  public void renderAll()
  {

	float f5 = 0.0625F;
    CentralBeam.render(f5);
    Base.render(f5);
    TopBase.render(f5);
    Table.render(f5);
    Rim1.render(f5);
    Rim2.render(f5);
    Rim3.render(f5);
    Rim4.render(f5);
    Brace1.render(f5);
    Brace2.render(f5);
    Brace3.render(f5);
    Brace4.render(f5);
  }
  
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
  
}
