package com.pauljoda.mobtools.rendering;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRepairAlter extends ModelBase
{
	//fields
	ModelRenderer Leg1;
	ModelRenderer Leg2;
	ModelRenderer Leg3;
	ModelRenderer Leg4;
	ModelRenderer Table;
	ModelRenderer Peg1;
	ModelRenderer Peg2;
	ModelRenderer Peg3;
	ModelRenderer Peg4;

	public ModelRepairAlter()
	{
		textureWidth = 64;
		textureHeight = 64;

		Leg1 = new ModelRenderer(this, 0, 0);
		Leg1.addBox(8F, -7F, 8F, 4, 12, 4);
		Leg1.setRotationPoint(3F, 11F, 3F);
		Leg1.setTextureSize(64, 64);
		Leg1.mirror = true;
		setRotation(Leg1, 0F, 0F, 0F);
		Leg2 = new ModelRenderer(this, 0, 0);
		Leg2.addBox(8F, -7F, 8F, 4, 12, 4);
		Leg2.setRotationPoint(-7F, 11F, 3F);
		Leg2.setTextureSize(64, 64);
		Leg2.mirror = true;
		setRotation(Leg2, 0F, 0F, 0F);
		Leg3 = new ModelRenderer(this, 0, 0);
		Leg3.addBox(8F, -7F, 8F, 4, 12, 4);
		Leg3.setRotationPoint(3F, 11F, -7F);
		Leg3.setTextureSize(64, 64);
		Leg3.mirror = true;
		setRotation(Leg3, 0F, 0F, 0F);
		Leg4 = new ModelRenderer(this, 0, 0);
		Leg4.addBox(8F, -7F, 8F, 4, 12, 4);
		Leg4.setRotationPoint(-7F, 11F, -7F);
		Leg4.setTextureSize(64, 64);
		Leg4.mirror = true;
		setRotation(Leg4, 0F, 0F, 0F);
		Table = new ModelRenderer(this, 0, 16);
		Table.addBox(8F, -11F, 8F, 14, 4, 14);
		Table.setRotationPoint(-7F, 11F, -7F);
		Table.setTextureSize(64, 64);
		Table.mirror = true;
		setRotation(Table, 0F, 0F, 0F);
		Peg1 = new ModelRenderer(this, 16, 0);
		Peg1.addBox(8F, -11F, 8F, 1, 1, 1);
		Peg1.setRotationPoint(-7F, 10F, -7F);
		Peg1.setTextureSize(64, 64);
		Peg1.mirror = true;
		setRotation(Peg1, 0F, 0F, 0F);
		Peg2 = new ModelRenderer(this, 16, 0);
		Peg2.addBox(8F, -11F, 8F, 1, 1, 1);
		Peg2.setRotationPoint(-7F, 10F, 6F);
		Peg2.setTextureSize(64, 64);
		Peg2.mirror = true;
		setRotation(Peg2, 0F, 0F, 0F);
		Peg3 = new ModelRenderer(this, 16, 0);
		Peg3.addBox(8F, -11F, 8F, 1, 1, 1);
		Peg3.setRotationPoint(6F, 10F, -7F);
		Peg3.setTextureSize(64, 64);
		Peg3.mirror = true;
		setRotation(Peg3, 0F, 0F, 0F);
		Peg4 = new ModelRenderer(this, 16, 0);
		Peg4.addBox(8F, -11F, 8F, 1, 1, 1);
		Peg4.setRotationPoint(6F, 10F, 6F);
		Peg4.setTextureSize(64, 64);
		Peg4.mirror = true;
		setRotation(Peg1, 0F, 0F, 0F);
	}

	public void renderAll()
	{
		Leg1.render(0.0625F);
		Leg2.render(0.0625F);
		Leg3.render(0.0625F);
		Leg4.render(0.0625F);
		Table.render(0.0625F);
		Peg1.render(0.0625F);
		Peg2.render(0.0625F);
		Peg3.render(0.0625F);
		Peg4.render(0.0625F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
