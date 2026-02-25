extends Area3D

const ROT_SPEDD = 2 # number of degree of coins rotate every frame

# Called when the node enters the scene tree for the first time.
func _ready() -> void:
	pass # Replace with function body.


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta: float) -> void:
	rotate_x(deg_to_rad(ROT_SPEDD))
	#if has_overlapping_bodies():
	#	queue_free()

func _on_body_entered(body: Node3D) -> void:
	queue_free()
