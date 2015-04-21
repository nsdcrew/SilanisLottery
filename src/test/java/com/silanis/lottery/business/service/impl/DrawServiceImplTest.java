/*
 * Created on 9 avr. 2015 ( Time 11:50:32 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.silanis.lottery.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.silanis.lottery.bean.Draw;
import com.silanis.lottery.bean.jpa.DrawEntity;
import java.util.Date;
import java.util.List;
import com.silanis.lottery.business.service.mapping.DrawServiceMapper;
import com.silanis.lottery.persistence.services.jpa.DrawPersistenceJPA;
import com.silanis.lottery.test.DrawFactoryForTest;
import com.silanis.lottery.test.DrawEntityFactoryForTest;
import com.silanis.lottery.test.MockValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test : Implementation of DrawService
 */
@RunWith(MockitoJUnitRunner.class)
public class DrawServiceImplTest {

	@InjectMocks
	private DrawServiceImpl drawService;
	@Mock
	private DrawPersistenceJPA drawPersistenceJPA;
	@Mock
	private DrawServiceMapper drawServiceMapper;
	
	private DrawFactoryForTest drawFactoryForTest = new DrawFactoryForTest();

	private DrawEntityFactoryForTest drawEntityFactoryForTest = new DrawEntityFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void findById() {
		// Given
		Integer idDraw = mockValues.nextInteger();
		
		DrawEntity drawEntity = drawPersistenceJPA.load(idDraw);
		
		Draw draw = drawFactoryForTest.newDraw();
		when(drawServiceMapper.mapDrawEntityToDraw(drawEntity)).thenReturn(draw);

		// When
		Draw drawFound = drawService.findById(idDraw);

		// Then
		assertEquals(draw.getIdDraw(),drawFound.getIdDraw());
	}

	@Test
	public void findAll() {
		// Given
		List<DrawEntity> drawEntitys = new ArrayList<DrawEntity>();
		DrawEntity drawEntity1 = drawEntityFactoryForTest.newDrawEntity();
		drawEntitys.add(drawEntity1);
		DrawEntity drawEntity2 = drawEntityFactoryForTest.newDrawEntity();
		drawEntitys.add(drawEntity2);
		when(drawPersistenceJPA.loadAll()).thenReturn(drawEntitys);
		
		Draw draw1 = drawFactoryForTest.newDraw();
		when(drawServiceMapper.mapDrawEntityToDraw(drawEntity1)).thenReturn(draw1);
		Draw draw2 = drawFactoryForTest.newDraw();
		when(drawServiceMapper.mapDrawEntityToDraw(drawEntity2)).thenReturn(draw2);

		// When
		List<Draw> drawsFounds = drawService.findAll();

		// Then
		assertTrue(draw1 == drawsFounds.get(0));
		assertTrue(draw2 == drawsFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		Draw draw = drawFactoryForTest.newDraw();

		DrawEntity drawEntity = drawEntityFactoryForTest.newDrawEntity();
		when(drawPersistenceJPA.load(draw.getIdDraw())).thenReturn(null);
		
		drawEntity = new DrawEntity();
		drawServiceMapper.mapDrawToDrawEntity(draw, drawEntity);
		DrawEntity drawEntitySaved = drawPersistenceJPA.save(drawEntity);
		
		Draw drawSaved = drawFactoryForTest.newDraw();
		when(drawServiceMapper.mapDrawEntityToDraw(drawEntitySaved)).thenReturn(drawSaved);

		// When
		Draw drawResult = drawService.create(draw);

		// Then
		assertTrue(drawResult == drawSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		Draw draw = drawFactoryForTest.newDraw();

		DrawEntity drawEntity = drawEntityFactoryForTest.newDrawEntity();
		when(drawPersistenceJPA.load(draw.getIdDraw())).thenReturn(drawEntity);

		// When
		Exception exception = null;
		try {
			drawService.create(draw);
		} catch(Exception e) {
			exception = e;
		}

		// Then
		assertTrue(exception instanceof IllegalStateException);
		assertEquals("already.exists", exception.getMessage());
	}

	@Test
	public void update() {
		// Given
		Draw draw = drawFactoryForTest.newDraw();

		DrawEntity drawEntity = drawEntityFactoryForTest.newDrawEntity();
		when(drawPersistenceJPA.load(draw.getIdDraw())).thenReturn(drawEntity);
		
		DrawEntity drawEntitySaved = drawEntityFactoryForTest.newDrawEntity();
		when(drawPersistenceJPA.save(drawEntity)).thenReturn(drawEntitySaved);
		
		Draw drawSaved = drawFactoryForTest.newDraw();
		when(drawServiceMapper.mapDrawEntityToDraw(drawEntitySaved)).thenReturn(drawSaved);

		// When
		Draw drawResult = drawService.update(draw);

		// Then
		verify(drawServiceMapper).mapDrawToDrawEntity(draw, drawEntity);
		assertTrue(drawResult == drawSaved);
	}

	@Test
	public void delete() {
		// Given
		Integer idDraw = mockValues.nextInteger();

		// When
		drawService.delete(idDraw);

		// Then
		verify(drawPersistenceJPA).delete(idDraw);
		
	}

}