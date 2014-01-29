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
pygame.display.set_caption('Composer')


redColor = pygame.Color(255,0,0)
blueColor = pygame.Color(0,0,255)
greenColor = pygame.Color(0,255,0)
whiteColor = pygame.Color(255,255,255)
blackColor = pygame.Color(0,0,0)


class scanner:
  x,y,h,w = 0
  
  def __init__(self,x,y,h,w):
    self.rect

scannerGroup = pygame.sprite.Group(  

#Create Sound object from file
beepSound = pygame.mixer.Sound('12925_sweet_trip_mm_sweep_x.wav')

#Main loop. 
while True:
  linePosX = linePosX+1
  
  #fill the surface with white color
  gameSurface.fill(blackColor)
  
  #Draw different shapes on the gameSurface object
  pygame.draw.circle(gameSurface, greenColor, (300,50), 20, 0)
  pygame.draw.line(gameSurface, whiteColor, (linePosX,linePosY), (linePosX,640))
  
  #pygame.event.get() will get a list of the events that happened since the last call
  for e in pygame.event.get():
	if e.type == QUIT:
	  #quit() is opposite of init()
	  pygame.quit()
	  sys.exit()
	
	elif e.type == KEYDOWN:
	  if e.key == K_ESCAPE:
	    pygame.event.post(pygame.event.Event(QUIT))
	
  #gameSurface will only be drawn after calling pygame.display.update()	
  pygame.display.update()
  #Wait for 30 frames per second
  fpsClock.tick(30)
  
