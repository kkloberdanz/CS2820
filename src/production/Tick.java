/**
 * @author Kyle Kloberdanz
 * Used to synchronize the actions in the warehouse
 */
package production;

interface Tick {
    void tick(int count);
}
