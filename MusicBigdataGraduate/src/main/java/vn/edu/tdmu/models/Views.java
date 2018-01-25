package vn.edu.tdmu.models;

/**
 * @author NguyenLinh
 */

public class Views{
    public static interface Summary{}
    public static interface ExtendedPublic extends Summary {}
    public static interface Internal extends ExtendedPublic {}
}