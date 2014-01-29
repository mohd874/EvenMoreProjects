#import pygame
import pygame, sys

#import constants such as QUIT, MOUSEMOTION, etc
from pygame.locals import *

#initialize pygame (keyboard, mouse, etc)
pygame.init()

#create Clock object to control the frames per second
fpsClock = pygame.time.Clock()

#display.set_mode() creates Surface object. This object is like the canvas where everything will be drawn.
gameSurface = pygame.display.set_mode((640,480))

#Set the window title
pygame.display.set_caption('Pygame Cheat Sheet')

#load image into surface object
ballSurface = pygame.image.load('ball.png')

redColor = pygame.Color(255,0,0)
blueColor = pygame.Color(0,0,255)
greenColor = pygame.Color(0,255,0)
whiteColor = pygame.Color(255,255,255)
blackColor = pygame.Color(0,0,0)

mouseX, mouseY = 0,0

#Create font object with size 32 pts
monacoFont = pygame.font.Font('MONACO1.TTF', 32)

msg = 'Hellow PyGame World!'

#Create Sound object from file
beepSound = pygame.mixer.Sound('12925_sweet_trip_mm_sweep_x.wav')

#Main loop. 
while True:
  #fill the surface with white color
  gameSurface.fill(whiteColor)
  
  #Draw different shapes on the gameSurface object
  pygame.draw.polygon(gameSurface, redColor, ((80,10),(40,20),(20,30),(10,40),(5,50)))
  pygame.draw.circle(gameSurface, greenColor, (300,50), 20, 0)
  pygame.draw.ellipse(gameSurface, blueColor, (300,250,40,80), 1)
  pygame.draw.rect(gameSurface, blackColor, (600,400, 30, 20), 0)
  
  #Draw individual pixels on the surface by creating a PixelArray object. After finishing the draw, the PixelArray object must be deleted.
  pixArr = pygame.PixelArray(gameSurface)
  for x in range(100, 200, 4):
    for y in range(100, 200, 4):
	  pixArr[x][y] = redColor
  del pixArr
  
  #copy the pixels from ballSurface to gameSurface. basicly draw the ballSurface on the gameSurface at spacific location
  gameSurface.blit(ballSurface, (mouseX, mouseY))
  
  #create a surface object with text written on it.
  msgSurface = monacoFont.render(msg, False, blueColor)
  msgRect = msgSurface.get_rect()
  msgRect.topleft = (20,50)
  gameSurface.blit(msgSurface, msgRect)
  
  #pygame.event.get() will get a list of the events that happened since the last call
  for e in pygame.event.get():
	if e.type == QUIT:
	  #quit() is opposite of init()
	  pygame.quit()
	  sys.exit()
	
	elif e.type == MOUSEMOTION:
	  mouseX, mouseY = e.pos
	elif e.type == MOUSEBUTTONUP:
	  mouseX, mouseY = e.pos
	  beepSound.play()
	  if e.button in (1,2,3):
	    msg = 'left, middle or right button was clicked'
	  elif e.button in (4,5):
	    msg = 'mouse scrolled up or down'
	
	elif e.type == KEYDOWN:
	  if e.key in (K_LEFT, K_RIGHT, K_UP, K_DOWN): #The K_* constants in pygame.locals represent the different keyboard keys
	    msg = 'Arrow key was pressed'
	  if e.key == K_a:
	    msg = 'A key was pressed'
	  if e.key == K_ESCAPE:
	    pygame.event.post(pygame.event.Event(QUIT))
	
  #gameSurface will only be drawn after calling pygame.display.update()	
  pygame.display.update()
  #Wait for 30 frames per second
  fpsClock.tick(30)
  
