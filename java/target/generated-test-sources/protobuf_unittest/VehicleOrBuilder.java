// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: com/google/protobuf/nested_builders_test.proto

package protobuf_unittest;

public interface VehicleOrBuilder extends
    // @@protoc_insertion_point(interface_extends:protobuf_unittest.Vehicle)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional .protobuf_unittest.Engine engine = 1;</code>
   */
  boolean hasEngine();
  /**
   * <code>optional .protobuf_unittest.Engine engine = 1;</code>
   */
  protobuf_unittest.Engine getEngine();
  /**
   * <code>optional .protobuf_unittest.Engine engine = 1;</code>
   */
  protobuf_unittest.EngineOrBuilder getEngineOrBuilder();

  /**
   * <code>repeated .protobuf_unittest.Wheel wheel = 2;</code>
   */
  java.util.List<protobuf_unittest.Wheel> 
      getWheelList();
  /**
   * <code>repeated .protobuf_unittest.Wheel wheel = 2;</code>
   */
  protobuf_unittest.Wheel getWheel(int index);
  /**
   * <code>repeated .protobuf_unittest.Wheel wheel = 2;</code>
   */
  int getWheelCount();
  /**
   * <code>repeated .protobuf_unittest.Wheel wheel = 2;</code>
   */
  java.util.List<? extends protobuf_unittest.WheelOrBuilder> 
      getWheelOrBuilderList();
  /**
   * <code>repeated .protobuf_unittest.Wheel wheel = 2;</code>
   */
  protobuf_unittest.WheelOrBuilder getWheelOrBuilder(
      int index);
}
