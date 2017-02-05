# The FROM instruction sets the Base Image for subsequent instructions.
# As such, a valid Dockerfile must have FROM as its first instruction.
# The image can be any valid image.

FROM java:8

# The ADD instruction copies new files, directories or remote file URLs
# from <src> and adds them to the filesystem of the image at the path <dest>.

ADD target/interconnect17-menu-1.jar app.jar

# The RUN instruction will execute any commands in a new layer
# on top of the current image and commit the results.
# The resulting committed image will be used for the next step in the Dockerfile.
# Layering RUN instructions and generating commits conforms to the core concepts
# of Docker where commits are cheap and containers can be created from
# any point in an image’s history, much like source control.

RUN bash -c 'touch /app.jar'

# The EXPOSE instruction informs Docker that the container listens on
# the specified network ports at runtime.
# EXPOSE does not make the ports of the container accessible to the host.
# To do that, you must use either the -p flag to publish a range of ports
# or the -P flag to publish all of the exposed ports.
# You can expose one port number and publish it externally under another number.

EXPOSE 8181

# An ENTRYPOINT allows you to configure a container
# that will run as an executable.
# ENTRYPOINT ["executable", "param1", "param2", ...]

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
