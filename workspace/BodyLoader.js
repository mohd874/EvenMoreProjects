/**
 * Loads the collision fixtures defined with the Physics Body Editor
 * application. You only need to give it a body and the corresponding fixture
 * name, and it will attach these fixtures to your body.
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com
 */
var bodyEditorLoader = {

	// Model
	model : {},

	// Reusable stuff
	vectorPool : [],
	polygonShape : {},//new PolygonShape();
	circleShape : {},//new CircleShape();
	vec : {},

	// -------------------------------------------------------------------------
	// Ctors
	// -------------------------------------------------------------------------

	//function init(FileHandle file) {
	//	if (file == null) throw new NullPointerException("file is null");
	//	model = readJson(file.readString());
	//}

	init : function (String str) {
		if (!str) return "str is null";
		model = readJson(str);
	},

	// -------------------------------------------------------------------------
	// Public API
	// -------------------------------------------------------------------------

	/**
	 * Creates and applies the fixtures defined in the editor. The name
	 * parameter is used to retrieve the right fixture from the loaded file.
	 * <br/><br/>
	 *
	 * The body reference point (the red cross in the tool) is by default
	 * located at the bottom left corner of the image. This reference point
	 * will be put right over the BodyDef position point. Therefore, you should
	 * place this reference point carefully to let you place your body in your
	 * world easily with its BodyDef.position point. Note that to draw an image
	 * at the position of your body, you will need to know this reference point
	 * (see {@link #getOrigin(java.lang.String, float)}.
	 * <br/><br/>
	 *
	 * Also, saved shapes are normalized. As shown in the tool, the width of
	 * the image is considered to be always 1 meter. Thus, you need to provide
	 * a scale factor so the polygons get resized according to your needs (not
	 * every body is 1 meter large in your game, I guess).
	 *
	 * @param body The Box2d body you want to attach the fixture to.
	 * @param name The name of the fixture you want to load.
	 * @param fd The fixture parameters to apply to the created body fixture.
	 * @param scale The desired scale of the body. The default width is 1.
	 */
	attachFixture: function attachFixture(body, name, fd, scale) {
		var rbModel = model.rigidBodies.get(name);
		if (!rbModel) return ("Name '" + name + "' was not found.");

		var origin = vec.set(rbModel.origin).mul(scale);

		for (var i=0, n=rbModel.polygons.length; i<n; i++) {
			var polygon = rbModel.polygons.get(i);
			var vertices = polygon.buffer;

			for (var ii=0, nn=vertices.length; ii<nn; ii++) {
				vertices[ii] = newVec().set(polygon.vertices.get(ii)).mul(scale);
				vertices[ii].sub(origin);
			}

			polygonShape.set(vertices);
			fd.shape = polygonShape;
			body.createFixture(fd);

			for (var ii=0, nn=vertices.length; ii<nn; ii++) {
				free(vertices[ii]);
			}
		}

		for (var i=0, n=rbModel.circles.length; i<n; i++) {
			var circle = rbModel.circles.get(i);
			var center = newVec().set(circle.center).mul(scale);
			var radius = circle.radius * scale;

			circleShape.setPosition(center);
			circleShape.setRadius(radius);
			fd.shape = circleShape;
			body.createFixture(fd);

			free(center);
		}
	},

	/**
	 * Gets the image path attached to the given name.
	 */
	getImagePath: function (name) {
		RigidBodyModel rbModel = model.rigidBodies.get(name);
		if (!rbModel) return ("Name '" + name + "' was not found.");

		return rbModel.imagePath;
	},

	/**
	 * Gets the origin point attached to the given name. Since the point is
	 * normalized in [0,1] coordinates, it needs to be scaled to your body
	 * size. Warning: this method returns the same Vector2 object each time, so
	 * copy it if you need it for later use.
	 */
	getOrigin: function (name, scale) {
		var rbModel = model.rigidBodies.get(name);
		if (!rbModel) return ("Name '" + name + "' was not found.");

		return vec.set(rbModel.origin).mul(scale);
	},

	/**
	 * <b>For advanced users only.</b> Lets you access the internal model of
	 * this loader and modify it. Be aware that any modification is permanent
	 * and that you should really know what you are doing.
	 */
	getInternalModel: function () {
		return model;
	},

	// -------------------------------------------------------------------------
	// Json Models
	// -------------------------------------------------------------------------

	Model: function() {
		this.rigidBodies = {};
	}

	RigidBodyModel: function() {
		this.name;
		this.imagePath;
		this.origin = {};
		this.polygons = [];
		this.circles = [];
	}

	PolygonModel: function() {
		this.vertices = [];
		this.buffer; //Type Vector2[]. used to avoid allocation in attachFixture()
	}

	CircleModel: function() {
		this.center = {};
		this.radius;
	}

	// -------------------------------------------------------------------------
	// Json reading process
	// -------------------------------------------------------------------------

	readJson: function (String str) {
		var m = new Model();
		var rootElem = JSON.parse(str);

		var bodiesElems = rootElem.rigidBodies;

		for (var i=0; i<bodiesElems.length; i++) {
			var bodyElem = bodiesElems[i];
			var rbModel = readRigidBody(bodyElem);
			m.rigidBodies.put(rbModel.name, rbModel);
		}

		return m;
	},

	readRigidBody: function (bodyElem) {
		var rbModel = new RigidBodyModel();
		rbModel.name = bodyElem.get("name");
		rbModel.imagePath = bodyElem.get("imagePath");

		var originElem = bodyElem.origin;
		rbModel.origin.x = originElem.x;
		rbModel.origin.y = originElem.y;

		// polygons

		var polygonsElem = bodyElem.polygons;

		for (var i=0; i<polygonsElem.length; i++) {
			var polygon = new PolygonModel();
			rbModel.polygons.add(polygon);

			var verticesElem = polygonsElem.get(i);
			for (var ii=0; ii<verticesElem.length; ii++) {
				var vertexElem = verticesElem.get(ii);
				var x = vertexElem.x;
				var y = vertexElem.y;
				polygon.vertices.add({x, y});
			}

			polygon.buffer = new Array[polygon.vertices.length];
		}

		// circles

		var circlesElem = bodyElem.circles;

		for (var i=0; i<circlesElem.length; i++) {
			var circle = new CircleModel();
			rbModel.circles.add(circle);

			var circleElem = circlesElem.[i];
			circle.center.x = circleElem.cx;
			circle.center.y = circleElem.cy;
			circle.radius = circleElem.r;
		}

		return rbModel;
	},

	// -------------------------------------------------------------------------
	// Helpers
	// -------------------------------------------------------------------------

	newVec: function () {
		return vectorPool.isEmpty() ? new Vector2() : vectorPool.remove(0);
	},

	free: function (v) {
		vectorPool.add(v);
	}
}
