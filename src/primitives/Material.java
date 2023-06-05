package primitives;

/**
 * Represents the material properties of an object in a 3D scene.
 */
public class Material {

    /** The diffuse coefficient of the material. */
    public Double3 kD = new Double3(0, 0, 0);

    /** The specular coefficient of the material. */
    public Double3 kS = new Double3(0, 0, 0);
    /** The shininess factor of the material. */
    public int nShines = 0;

    /**
     * Sets the diffuse coefficient of the material.
     *
     * @param kd the diffuse coefficient to be set
     * @return the Material object with the diffuse coefficient set
     */
    public Material setKd(Double3 kd) {
        this.kD = kd;
        return this;
    }
    /**
     * Sets the diffuse coefficient of the material.
     *
     * @param kd the diffuse coefficient to be set
     * @return the Material object with the diffuse coefficient set
     */
    public Material setKd(Double kd) {
        this.kD =new Double3(kd) ;
        return this;
    }

    /**
     * Sets the specular coefficient of the material.
     *
     * @param ks the specular coefficient to be set
     * @return the Material object with the specular coefficient set
     */
    public Material setKs(Double3 ks) {
        this.kS = ks;
        return this;
    }
    /**
     * Sets the specular coefficient of the material.
     *
     * @param ks the specular coefficient to be set
     * @return the Material object with the specular coefficient set
     */
    public Material setKs(Double ks) {
        this.kS =new Double3(ks);
        return this;
    }
    /**
     * Sets the shininess factor of the material.
     * @param nShines the shininess factor to be set
     * @return the Material object with the shininess factor set
     */
    public Material setnShines(int nShines) {
        this.nShines = nShines;
        return this;
    }

}
