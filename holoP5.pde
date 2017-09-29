import holoP5.HoloP5;
import shapes3d.*;

HoloP5 holoP5;

float rot = 75f;
float rotX = rot * 0.5f;
float rotY = rot * 0.1f;
float rotZ = rot * 0.3f;


Ellipsoid earth, moon;
Box orbiter;

void setup() {
  size(800, 800, P3D);
  holoP5 = new HoloP5(this);
  holoP5.renderHologram2();
  holoP5.setBackgroundColor(0);
//  camera3D.enableSaveFrame('s', "debug");

  earth = new Ellipsoid(this, 16, 16);
  earth.setTexture("earth.jpg");
  earth.setRadius(80);
  earth.moveTo(new PVector(0, 0, 0));
  earth.strokeWeight(1.0f);
  earth.stroke(color(255, 255, 0));
//  earth.moveTo(2, 3, -1);
  earth.tag = "Earth";
  earth.drawMode(Shape3D.TEXTURE);
  
  moon = new Ellipsoid(this, 15, 15);
  moon.setTexture("moon.jpg");
  moon.drawMode(Shape3D.TEXTURE);
  moon.setRadius(20);
  moon.moveTo(0, 0, 100);
  moon.tag = "Moon";

  orbiter = new Box(this, 6, 6, 10);
  orbiter.fill(color(32, 32, 200));
  orbiter.stroke(color(200, 200, 200));
  orbiter.strokeWeight(1.2f);
  orbiter.moveTo(0, 0, -30);
  orbiter.drawMode(Shape3D.SOLID);
  orbiter.tag = "orbiter";

  earth.addShape(moon);
  moon.addShape(orbiter);
}

void preDraw() {
  rot += 6;
  rotX = rot;
//  rotX = rot * 0.5f;
  rotY = rot * 0.5f;
  rotZ = rot * 0.5f;
}

void draw() {
  pushMatrix();
  translate(width / 2, height / 2);
  rotateX(radians(rotX));
//  rotateY(radians(rotY));
//  rotateZ(radians(rotZ));

  moon.rotateBy(radians(1.5f), 0, 0);
  orbiter.rotateBy(0, radians(3.0f), 0);

  earth.draw();

  popMatrix();
}

void postDraw() {
  if (frameCount <= 120) saveFrame("screen-####.png");
}