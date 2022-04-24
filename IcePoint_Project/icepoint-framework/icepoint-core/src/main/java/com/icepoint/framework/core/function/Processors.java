package com.icepoint.framework.core.function;

import org.springframework.data.util.Lazy;

/**
 * @author Jiawei Zhao
 */
public class Processors {

    private Processors() {
    }

    private static final Lazy<BatchObjMergeByField> BATCH_OBJ_Merge_ByFIELD = Lazy.of(BatchObjMergeByField::new);

    private static final Lazy<BatchObjSplitByField> BATCH_OBJ_SPLIT_ByFIELD = Lazy.of(BatchObjSplitByField::new);

    private static final Lazy<ConvertTreeBranch> CONVERT_TREE_BRANCH = Lazy.of(ConvertTreeBranch::new);

    private static final Lazy<LinesToColumns> LISTS_TO_COLUMNS = Lazy.of(LinesToColumns::new);

    private static final Lazy<ListAddMap> LIST_ADD_MAP = Lazy.of(ListAddMap::new);

    private static final Lazy<ListContainObj> LIST_CONTAIN_OBJ = Lazy.of(ListContainObj::new);
    
    private static final Lazy<ListGrpByFld> LIST_GRP_BY_FID = Lazy.of(ListGrpByFld::new);

    private static final Lazy<ListNum> LIST_NUM = Lazy.of(ListNum::new);

    private static final Lazy<ListsMerger> LISTS_MERGER = Lazy.of(ListsMerger::new);

    private static final Lazy<ListStats> LIST_STATS = Lazy.of(ListStats::new);
    
    private static final Lazy<ListToFieldArray> LIST_TO_FIELD_ARRAY = Lazy.of(ListToFieldArray::new);
    
    private static final Lazy<ListToString> LIST_TO_STRING = Lazy.of(ListToString::new);

    private static final Lazy<MakeMap> MAKE_MAP = Lazy.of(MakeMap::new);

    private static final Lazy<MapJoin> MAP_JOIN = Lazy.of(MapJoin::new);

    public static BatchObjMergeByField batchObjMergeByField() {
        return BATCH_OBJ_Merge_ByFIELD.get();
    }
    
    public static BatchObjSplitByField batchObjSplitByField() {
        return BATCH_OBJ_SPLIT_ByFIELD.get();
    }

    public static ConvertTreeBranch convertTreeBranch() {
        return CONVERT_TREE_BRANCH.get();
    }
    
    public static LinesToColumns linesToColumns() {
        return LISTS_TO_COLUMNS.get();
    }

    public static ListAddMap listAddMap() {
        return LIST_ADD_MAP.get();
    }

    public static ListContainObj listContainObj() {
        return LIST_CONTAIN_OBJ.get();
    }

    public static ListGrpByFld listGrpByFld() {
        return LIST_GRP_BY_FID.get();
    }

    public static ListNum listNum() {
        return LIST_NUM.get();
    }

    public static ListsMerger listsMerger() {
        return LISTS_MERGER.get();
    }

    public static ListStats listStats() {
        return LIST_STATS.get();
    }

    public static ListToFieldArray listToFieldArray() {
        return LIST_TO_FIELD_ARRAY.get();
    }
    
    public static ListToString listToString() {
        return LIST_TO_STRING.get();
    }

    public static MakeMap makeMap() {
        return MAKE_MAP.get();
    }

    public static MapJoin mapJoin() {
        return MAP_JOIN.get();
    }

}
