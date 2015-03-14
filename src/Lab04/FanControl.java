/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Lab04;

/**
 *
 * @author Dinanajana
 */
public class FanControl {
    
    
    enum FanState{
        
        OFF{
            FanState eval(String cmd){
            
                if(cmd == "inc"){
                
                    return MED;
                }
                else if(cmd == "dec"){
                
                    return OFF;
                }
                return null;
            }
        }
        ,MED{
            FanState eval (String cmd){
                if(cmd == "inc"){
                
                    return HI;
                }else if(cmd == "dec"){
                
                    return OFF;
                }
                
                return null;
            }
        }
        ,HI{
            FanState eval ()
        
        }
    
    }
    
}
