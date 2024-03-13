package reflection;

import java.util.*;
import java.lang.reflect.*;

/**
 * This program uses reflection to print all features of a class.
 * 
 * @version 1.12 2021-06-15
 * @author Cay Horstmann
 */

public class ReflectionTest {

    public static void main(String[] args) {
        // read class name from command line ars or user input
        String name;

        if (args.length > 0) {
            name = args[0];
        } else {
            var in = new Scanner(System.in);
            System.out.println("Enter class name (e.g. java.util.Date): ");
            name = in.next();
        }

        try {
            // print class modifiers, name, and superclass name (if != Object)
            Class cl = Class.forName(name);
            String modifiers = Modifier.toString(cl.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            if (cl.isSealed()) {
                System.out.print("sealed ");
            }

            if (cl.isEnum()) {
                System.out.print("enum " + name);
            } else if (cl.isRecord()) {
                System.out.print("record " + name);
            } else if (cl.isInterface()) {
                System.out.print("interface " + name);
            } else {
                System.out.print("class " + name);
            }

            Class<?> supercl = cl.getSuperclass();

            if (supercl != null && supercl != Object.class) {
                System.out.print(" extends " + supercl.getName());
            }

            // printInterfaces(cl);
            printPermittedSubclasses(cl);

            System.out.print("\n{\n");
            printConstructors(cl);
            System.out.println();
            printMethods(cl);
            System.out.println();
            printFields(cl);
            System.out.println("}");
        } catch (ClassNotFoundException e) {
            System.out.println("Error");
        }
    }

    /**
     * Print all constructors of a class
     * 
     * @param cl a class
     */
    public static void printConstructors(Class cl) {
        Constructor<?>[] constructors = cl.getConstructors();

        for (Constructor<?> constructor : constructors) {
            String name = constructor.getName();
            String modifiers = Modifier.toString(constructor.getModifiers());

            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(name + "(");

            // print parameter types
            Class[] paramTypes = constructor.getParameterTypes();

            for (int i = 0; i < paramTypes.length; i++) {

                if (i > 0)
                    System.out.print(", ");
                System.out.print(paramTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * Print all methods of a class
     * 
     * @param cl a class
     */
    public static void printMethods(Class<?> cl) {
        Method[] methods = cl.getMethods();

        for (Method m : methods) {
            Class retType = m.getReturnType();
            String name = m.getName();

            // print modifiers, return type and method name
            String modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + ", ");
            }
            System.out.print(retType.getName() + " " + name + "(");

            Class[] paramTypes = m.getParameterTypes();

            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }

                System.out.print(paramTypes[i].getName());
            }

            System.out.println(")");
        }
    }

    public static void printFields(Class<?> cl) {
        Field[] fields = cl.getFields();

        System.out.println("These are the fields: ");

        for (Field field : fields) {
            Class type = field.getType();
            String fieldName = field.getName();
            System.out.print(" ");

            String modifiers = Modifier.toString(field.getModifiers());k

            if (modifiers.length() > 0) {
                System.out.print(modifiers + ", ");
            }
            System.out.println(type.getName() + " " + fieldName + ";");
        }
        System.out.println();
    }

    public static void printPermittedSubclasses(Class<?> cl) {
        if (cl.isSealed()) {
            Class<?>[] permittedSubClasses = cl.getPermittedSubclasses();

            for (int i = 0; i < permittedSubClasses.length; i++) {
                if (i == 0) {
                    System.out.print(" permits ");
                } else {
                    System.out.print(", ");
                }
                System.out.print(permittedSubClasses[i].getName());
            }
        }
        System.out.println();
    }

    public static void printInterfaces(Class<?> cl) {
        Class<?>[] interfaces = cl.getInterfaces();

        System.out.print("implements ");

        for (int i = 0; i < interfaces.length; i++) {
            if (i == 0) {
                System.out.print(cl.isInterface() ? " extends " : " implements ");
            }

            System.out.print(interfaces[i].getName());
        }
    }
}