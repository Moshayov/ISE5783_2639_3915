package primitives;

/**
 * Represents the material properties of an object in a 3D scene.
 */
public class Material {

    /**
     * The diffuse coefficient of the material.
     */
    private Double3 kD = Double3.ZERO;

    /**
     * The specular coefficient of the material.
     */
    private Double3 kS = Double3.ZERO;
    /**
     * The shininess factor of the material.
     */
    public int nShines = 0;


    //Attenuation coefficient of transparency
   public Double3 Kt = Double3.ZERO;
   //attenuation coefficient of reflection
   public Double3 Kr = Double3.ZERO;
    /**
     * Sets the transmission coefficient for the material.
     *
     * @param kt The transmission coefficient to set
     * @return The updated Material object
     */
    public Material setKt(Double3 kt) {
        this.Kt = kt;
        return this;
    }
    /**
     * Sets the reflection coefficient for the material.
     *
     * @param kr The reflection coefficient to set
     * @return The updated Material object
     */
    public Material setKr(Double3 kr) {
        this.Kr = kr;
        return this;
    }
    /**
     * Sets the transmission coefficient for the material using a single value.
     * The value will be converted to a Double3 object with all components set to the same value.
     *
     * @param kt The transmission coefficient to set
     * @return The updated Material object
     */
    public Material setKt(Double kt) {
        this.Kt = new Double3(kt);
        return this;
    }
    /**
     * Sets the reflection coefficient for the material using a single value.
     * The value will be converted to a Double3 object with all components set to the same value.
     *
     * @param kr The reflection coefficient to set
     * @return The updated Material object
     */
    public Material setKr(Double kr) {
        this.Kr = new Double3(kr);
        return this;
    }


    /**
     * Sets the shininess factor of the material.
     *
     * @param nShines the shininess factor to be set
     * @return the Material object with the shininess factor set
     */
    public Material setnShines(int nShines) {
        this.nShines = nShines;
        return this;
    }

    /**
     * Getter for Ks field.
     *
     * @return The value of the specular reflectivity.
     */
    public Double3 getKs() {
        return this.kS;
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
        this.kS = new Double3(ks);
        return this;
    }

    /**
     * Getter for Kd field.
     *
     * @return The value of diffuse reflectivity.
     */
    public Double3 getKd() {
        return kD;
    }

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
        this.kD = new Double3(kd);
        return this;
    }

}
