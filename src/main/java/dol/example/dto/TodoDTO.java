package dol.example.dto;

import dol.example.common.TodoInfo;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class TodoDTO implements Serializable {
    private TodoInfo todoInfo;
    private SymbolDTO symbolDTO;

    public TodoDTO(TodoInfo todoInfo){
        this.todoInfo = todoInfo;
    }

    public TodoDTO(TodoInfo todoInfo, SymbolDTO symbolDTO){
        if(isTodoOfSymbol(todoInfo)){
            this.todoInfo = todoInfo;
            this.symbolDTO = symbolDTO;
        }
    }

    public void setSymbolDTO(SymbolDTO symbolDTO){
        if(isTodoOfSymbol(todoInfo)){
            this.symbolDTO = symbolDTO;
        }
    }

    /**
     * 아케인 심볼, 어센틱 심볼 관련한 메할일만 SymbolDTO로 생성 가능
     * @return
     */
    private boolean isTodoOfSymbol(TodoInfo todoInfo){
        if(!TodoInfo.getTodoInfoListForArcaneSymbol().contains(todoInfo)
                || !TodoInfo.getTodoInfoListForAuthenticSymbol().contains(todoInfo)){
            return false;
        }
        return true;
    }
}
